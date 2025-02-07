package com.github.schmittjoaopedro.algorithms.localsearch;

import com.github.schmittjoaopedro.MathUtils;
import com.github.schmittjoaopedro.algorithms.operators.insertion.InsertPosition;
import com.github.schmittjoaopedro.algorithms.operators.insertion.InsertionService;
import com.github.schmittjoaopedro.algorithms.operators.insertion.RouteTimes;
import com.github.schmittjoaopedro.problem.Instance;
import com.github.schmittjoaopedro.problem.Request;
import com.github.schmittjoaopedro.problem.Solution;
import com.github.schmittjoaopedro.problem.SolutionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RelocateRequests {

    private Instance instance;

    private InsertionService insertionService;

    public RelocateRequests(Instance instance) {
        this.instance = instance;
        this.insertionService = new InsertionService(instance);
    }

    public Solution relocate(Solution solution) {
        int bestVehicle = -1;
        Request request;
        InsertPosition bestInsertion, insertPosition;
        Set<Integer> solutionHashes = new HashSet<>();
        Solution tempSol = SolutionUtils.createSolution(instance);
        SolutionUtils.copyFromTo(solution, tempSol);
        RouteTimes[] routeTimes = new RouteTimes[tempSol.tours.size()];
        for (int k = 0; k < tempSol.tours.size(); k++) {
            routeTimes[k] = new RouteTimes(tempSol.tours.get(k), instance);
            tempSol.indexVehicle(k);
        }
        boolean improvement = true;
        while (improvement) {
            improvement = false;
            for (Integer requestId = 0; requestId < instance.numRequests; requestId++) {
                request = instance.requests[requestId];
                if (request.isVehicleRelocatable()) {
                    int vehicle = tempSol.getVehicle(request.pickupTask.nodeId);
                    bestInsertion = new InsertPosition();
                    for (int k = 0; k < tempSol.tours.size(); k++) {
                        if (k != vehicle) {
                            insertPosition = insertionService.calculateBestPosition(tempSol.tours.get(k), request, routeTimes[k]);
                            if (insertPosition.cost < bestInsertion.cost) {
                                bestInsertion = insertPosition;
                                bestVehicle = k;
                            }
                        }
                    }
                    if (bestInsertion.cost < Double.MAX_VALUE) {
                        int hash = getHashCost(bestInsertion, bestVehicle);
                        if (!solutionHashes.contains(hash)) {
                            solutionHashes.add(hash);
                            double removalGain = tempSol.calculateRequestRemovalGain(instance, request);
                            if (bestInsertion.cost < removalGain) {
                                // Remove request from old vehicle
                                tempSol.remove(Arrays.asList(requestId), instance);
                                routeTimes[vehicle] = new RouteTimes(tempSol.tours.get(vehicle), instance);
                                tempSol.indexVehicle(vehicle);
                                // Insert request on new vehicle
                                tempSol.insert(instance, requestId, bestVehicle, bestInsertion.pickupPos, bestInsertion.deliveryPos);
                                routeTimes[bestVehicle] = new RouteTimes(tempSol.tours.get(bestVehicle), instance);
                                tempSol.indexVehicle(bestVehicle);
                                improvement = true;
                            }
                        }
                    }
                }
            }
        }
        instance.solutionEvaluation(tempSol);
        return tempSol;
    }

    public int getHashCost(InsertPosition insertPosition, Integer vehicle) {
        return ("-" + vehicle +
                "-" + MathUtils.round(insertPosition.cost) +
                "-" + insertPosition.deliveryPos +
                "-" + insertPosition.pickupPos).hashCode();
    }
}

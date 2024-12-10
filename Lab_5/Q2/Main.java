interface WaterConservationSystem {
    int calculateTrappedWater(int[] blockHeights);
}

abstract class RainySeasonConservation implements WaterConservationSystem {
}

class CityBlockConservation extends RainySeasonConservation {
    @Override
    public int calculateTrappedWater(int[] blockHeights) {
        if (blockHeights == null || blockHeights.length <= 2) {
            return 0;
        }

        int left = 0, right = blockHeights.length - 1;
        int leftMax = 0, rightMax = 0;
        int totalWater = 0;
        
        while (left < right) {
            if (blockHeights[left] < blockHeights[right]) {
                if (blockHeights[left] >= leftMax) {
                    leftMax = blockHeights[left];
                } else {
                    totalWater += leftMax - blockHeights[left];
                }
                left++;
            } else {
                if (blockHeights[right] >= rightMax) {
                    rightMax = blockHeights[right];
                } else {
                    totalWater += rightMax - blockHeights[right];
                }
                right--;
            }
        }
        
        return totalWater;
    }
}

class Main {
    public static void main(String[] args) {
        CityBlockConservation conservation = new CityBlockConservation();
        int[] blocks = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int trappedWater = conservation.calculateTrappedWater(blocks);
        System.out.println("Trapped water: " + trappedWater);
    }
}
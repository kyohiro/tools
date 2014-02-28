#include <stdio.h>
#include <iostream>


void quicksort(int array[], int low, int high) {
	int left = low, right = high, mid = array[(low + high) >> 1];
	while (left < right) {
		while (array[left] < mid) ++left;
		while (array[right] > mid) --right;
		if (left <= right) {
			int tmp = array[left];
			array[left] = array[right];
			array[right] = tmp;
			++left;
			--right;
		}
	}
	if (left < high) quicksort(array, left, high);
	if (low < right) quicksort(array, low, right);
}

int main() {
	int array[] = {2, 1, 3, 6, 5, 3};	
	quicksort(array, 0, 5);
	for (auto i : array) {
		std::cout << i << " ";
	}
	std::cout << std::endl;
	return 0;
}

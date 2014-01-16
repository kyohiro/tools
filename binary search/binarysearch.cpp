#include <stdio.h>
#include <string>
#include <iostream>

int search(int value, int array[], int length) {
	if (array == NULL) {
		return -1;
	}

	int left = 0;
	int right = length - 1;

	while (left <= right) {
		int middle = left + ((right - left) >> 1);
		if (array[middle] > value) {
			right = middle - 1;
		}
		else if (array[middle] < value) {
			left = middle + 1;
		}
		else {
			return middle;
		}
	}
	return -1;
}

template <typename T>
int genericSearch(T value, T array[], int length) {
	if (array == NULL) {
		return -1;
	}
	int left = 0;
	int right = length - 1;

	while (left <= right) {
		int middle = left + ((right - left) >> 1);
		if (array[middle] > value) {
			right = middle - 1;
		}
		else if (array[middle] < value) {
			left = middle + 1;
		}
		else {
			return middle;
		}
	}
	return -1;
}


int main() {
	int a[6] = {1, 3, 5, 7, 9, 25};
	std::cout << search(3, a, 6) << " should be equals to 1" << std::endl;

	std::string b[4] = {"something", "testing", "uva", "uzw"};
	std::string v = "uzw";
	std::cout << genericSearch(v, b, 4) << " should be equal to 3" << std::endl;

	return 0;
}


#include <iostream>
#include <thread>
#include <ctime>
#include <chrono>
#include <cmath>
#include <mutex>
#include <vector>
#include <cstdint>

unsigned long long int number(0);
unsigned long long int sqrtnumber(0);
std::atomic<unsigned long long int> index(2);
std::mutex mtx;
std::atomic<bool> sqrtNumberIsLowerThanIndex(true);
std::atomic<bool> isprime(true);

void isPrime0(unsigned long long int begin, unsigned long long int end)
{
	if (!sqrtNumberIsLowerThanIndex)
		return;
	std::unique_lock<std::mutex> lock(mtx, std::defer_lock);
	for (unsigned long long int i = begin; i <= end; i++)
	{
		if (!isprime)
			return;
		if (number % i == 0)
		{
			isprime = false;
			return;
		}
	}
	if (index + 100 > sqrtnumber)
	{
		lock.lock();
		sqrtNumberIsLowerThanIndex = false;
		unsigned long long int tempnumber = sqrtnumber - index;
		lock.unlock();
		isPrime0(index, index += tempnumber);
		return;
	}
	isPrime(index, index += 100);
}

void isPrime(unsigned long long int begin, unsigned long long int end)
{
	std::this_thread::sleep_for(std::chrono::milliseconds(1));
	for (unsigned long long int i = begin; i <= end; i++)
	{
		//std::this_thread::sleep_for(std::chrono::milliseconds(1));
		if (!isprime)
			return;
		if (number % i == 0)
		{
			isprime = false;
			return;
		}
	}
}

int main()
{
	//std::cout << "The number of concurrent threads is " << std::thread::hardware_concurrency() << "\n";
	std::cout << "Enter a number: ";
	std::cin >> number;
	sqrtnumber = std::trunc(std::sqrt(number));

	int arrSize;
	std::cout << "Enter number of threads(from 1 to 10): ";
	std::cin >> arrSize;
	if (arrSize < 0)
		return 0;
	if (sqrtnumber < arrSize)
		arrSize = 1;
	std::vector<std::thread> arrayOfThreads;

	auto start_time = std::chrono::system_clock::now();

	//int tempnumber = (int)std::round(sqrtnumber / arrSize);

	//arrayOfThreads.push_back(std::thread(isPrime, sqrtnumber, 2, sqrtnumber / arrSize));

	for (unsigned long long int i = 1; i < arrSize; i++)
	{
		arrayOfThreads.push_back(std::thread(isPrime0, index, index += 100));
	}

	for (unsigned long long int i = 2; i <= sqrtnumber / arrSize; i++)
	{
		//std::this_thread::sleep_for(std::chrono::milliseconds(1));
		if (!isprime)
			break;
		if (number % i == 0)
		{
			isprime = false;
			break;
		}
	}

	for (int i = 0; i < arrSize-1; i++)
	{
		arrayOfThreads[i].join();
	}

	if (isprime)
		std::cout << number << " is prime" << std::endl;
	else
		std::cout << number << " is not prime" << std::endl;

	auto end_time = std::chrono::system_clock::now();
	auto dif = end_time - start_time;

	std::cout << std::endl;
	std::cout << "Time: " << std::chrono::duration_cast<std::chrono::milliseconds>(dif).count() << std::endl;
	return 0;
}
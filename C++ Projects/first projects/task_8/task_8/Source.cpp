#include <iostream>
#include "IntStack.h"

int main()
{
	IntStack myStack1;
	IntStack myStack2;
	myStack1.pushBackTheElement(0);
	myStack1.pushBackTheElement(1);
	myStack1.pushBackTheElement(2);
	myStack1.pushBackTheElement(3);
	myStack1.pushBackTheElement(4);
	myStack1.pushBackTheElement(5);
	myStack1.pushBackTheElement(6);
	myStack1.pushBackTheElement(7);
	std::cout << myStack1 << std::endl;
	myStack1.popBackElement();
	myStack1.popBackElement();
	myStack1.popBackElement();
	myStack1.popBackElement();
	myStack1.popBackElement();
	myStack1.popBackElement();
	std::cout << myStack1 << std::endl;
	myStack1.popBackElement();
	std::cout << myStack1 << std::endl;
	return 0;
}
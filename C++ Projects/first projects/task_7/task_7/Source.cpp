#include <iostream>
#include "LinkedList.h"

int main()
{
	LinkedList myList1;
	/*myList1.pushBack(1);
	myList1.pushBack(2);
	myList1.pushBack(0);
	myList1.pushBack(14);
	myList1.pushBack(5);*/
	std::cout << "Sum of the elements with even ordinal numbers: "
		<< myList1.summOfElements() << std::endl;
	if (myList1.isEmpty())
		myList1.pushBackFirstElement();
	myList1.print();
	int number;
	std::cin >> number;
	//if (!myList1.isEmpty())
		std::cout << "The " << number << "'th element from the end: "
		<< myList1.findNElement(number) << std::endl;
	return 0;
}
#include <iostream>
#include "tree.h"


int main()
{
	Tree myTree;
	myTree.addElement(20);
	myTree.addElement(10);
	myTree.addElement(15);
	myTree.addElement(17);
	myTree.addElement(18);
	myTree.addElement(16);
	myTree.addElement(13);
	myTree.addElement(14);
	myTree.addElement(12);
	myTree.addElement(5);
	myTree.addElement(7);
	myTree.addElement(8);
	myTree.addElement(6);
	myTree.addElement(3);
	myTree.addElement(4);
	myTree.addElement(2);
	myTree.addElement(23);
	myTree.addElement(22);
	myTree.print();
	std::cout << std::endl;
	myTree.eraseElement(20);
	myTree.print();
	return 0;
}

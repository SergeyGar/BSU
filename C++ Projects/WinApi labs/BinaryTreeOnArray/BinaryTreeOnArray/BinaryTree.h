#pragma once

#include <iostream>
#include <utility>

class BinaryTree
{
private:
	const int _MULTIPLIER = 2;
	std::pair<int,bool>* _tree;
	int _size = 1;
	int _counterOfElements = 0;

	void changeLength(int);
public:
	BinaryTree();
	BinaryTree(int value);
	BinaryTree(const BinaryTree&);
	BinaryTree(BinaryTree&&);
	BinaryTree(const std::initializer_list<int>);
	~BinaryTree();
	void addElement(int);
	void addElement(int, int);
	void popElement(int);
	void popElement(int, int);
	int getRoot();
	bool isEmpty();
};


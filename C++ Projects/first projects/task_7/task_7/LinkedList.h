#pragma once

class LinkedList
{
private:
	struct Node
	{
		Node(int value, Node* next, Node* previous) : value(value), next(next) {}
		int value;
		Node* next;
	};

	Node* _head;
	int _size;

public:
	LinkedList();
	LinkedList(const LinkedList&);
	LinkedList(LinkedList&&);
	~LinkedList();
	void pushBack(int);
	void pushBackFirstElement();
	void pushFront(int);
	void print() const;
	int findNElement(int);
	int summOfElements();
	bool isEmpty();
};
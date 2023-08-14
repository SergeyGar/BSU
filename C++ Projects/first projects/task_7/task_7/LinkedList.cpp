#include <iostream>
#include "LinkedList.h"

LinkedList::LinkedList() : _head(nullptr), _size(0) {}

LinkedList::LinkedList(const LinkedList& list) {
	auto currentNodeForThisList = _head;
	auto currentNodeForAnotherList = list._head;
	if (!currentNodeForThisList) {

	}

	while (currentNodeForAnotherList -> next) {
		if (!currentNodeForThisList -> next) {
			currentNodeForThisList->next = new Node ();
		}
		currentNodeForThisList -> value = currentNodeForAnotherList -> value;
		currentNodeForAnotherList = currentNodeForAnotherList -> next;
	}

}

LinkedList::LinkedList(LinkedList&& list) {

}

void LinkedList::pushBack(int value)
{
	if (!_head)
	{
		_head = new Node(value, nullptr, nullptr);
	}
	else
	{
		auto current = _head;
		while (current -> next)
		{
			current = current -> next;
		}
		current->next = new Node(value, nullptr, current);
	}
}

void LinkedList::pushFront(int value)
{
	_head = new Node(value, _head, nullptr);
}

void LinkedList::print() const
{
	std::cout << "Elements of list: " << std::endl;
	auto current = _head;
	while (current)
	{
		std::cout << current->value << ' ';
		current = current->next;
	}
	std::cout << std::endl;
}

int LinkedList::summOfElements()
{
	if (!_head)
		return 0;
	int i = 1;
	int summ = 0;
	Node* current = _head;
	while (current->next)
	{
		if (i % 2 == 0)
		{
			summ += current->value;
		}
		current = current->next;
		i++;
	}
	return summ;
}

void LinkedList::pushBackFirstElement()
{
	if (!isEmpty())
		pushBack(_head->value);
	else
		return;
}

int LinkedList::findNElement(int number)
{
	if (isEmpty())
		return 0;
	if (number < 0)
		return 0;
	auto pointer1 = _head;
	auto pointer2 = _head;
	for (int i = 0; i < number - 1; i++)
	{
		if (pointer2 == nullptr)
			return 0;
		pointer2 = pointer2->next;
	}
	while (pointer2->next)
	{
		pointer2 = pointer2->next;
		pointer1 = pointer1->next;
	}
	return pointer1->value;
}

bool LinkedList::isEmpty()
{
	if (_head)
		return false;
	else
		return true;
}

LinkedList::~LinkedList()
{
	while (_head)
	{
		auto tempNode = _head->next;
		delete _head;
		_head = tempNode;
	}
}
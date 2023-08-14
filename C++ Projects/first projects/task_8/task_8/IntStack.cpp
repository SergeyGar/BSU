#include <iostream>
#include "IntStack.h"

IntStack::IntStack()
{
	_stack = new int[1];
	_size = 0;
	_counterOfElements = 0;
};

IntStack::IntStack(int value)
{
	_stack = new int[1];
	_size = 0;
	_counterOfElements = 0;
	pushBackTheElement(value);
};

IntStack::IntStack(const IntStack& stack)
{
	_size = stack._size;
	_counterOfElements = stack._counterOfElements;
	delete[] _stack;
	_stack = new int[_size];
	for (int i = 0; i < _size; i++)
	{
		_stack[i] = stack._stack[i];
	}
}

IntStack::IntStack(IntStack&& stack)
{
	_stack = stack._stack;
	_size = stack._size;
	_counterOfElements = stack._counterOfElements;
	stack._stack = nullptr;
	stack._size = 0;
	stack._counterOfElements = 0;
}

bool IntStack::isEmpty()
{
	return _counterOfElements == 0;
}

int IntStack::numberOfElements()
{
	return _counterOfElements;
}

int IntStack::takeTheTopElement()
{
	if (!isEmpty())
		return _stack[_counterOfElements - 1];
}

void IntStack::pushBackTheElement(int element)
{
	if (_counterOfElements == 0 && _size == 0)
	{
		_stack[0] = element;
		_size++;
		_counterOfElements++;
		return;
	}
	if (_counterOfElements == _size)
	{
		changeLengthOfStack(_size * 2);
	}
	_stack[_counterOfElements] = element;
	_counterOfElements++;
	return;
}

void IntStack::popBackElement()
{
	if (_counterOfElements == 0)
		return;
	if (_size / (_counterOfElements) >= 4)
	{
		changeLengthOfStack(_size / 2);
	}
	_counterOfElements--;
}

void IntStack::changeLengthOfStack(int newSize)
{
	int* newStack = new int[newSize];
	for (int i = 0; i < _counterOfElements; i++)
	{
		newStack[i] = _stack[i];
	}
	delete[] _stack;
	_size = newSize;
	_stack = newStack;
}

IntStack& IntStack::getStack()
{
	return *this;
}

std::ostream& operator<< (std::ostream& output, const IntStack& stack)
{
	int counter = stack._size;
	for (int i = 0; i < counter; i++)
	{
		output << stack._stack[i] << ' ';
	}
	return output;
}

std::istream& operator>> (std::istream& input, IntStack& stack)
{
	int counter;
	input >> counter;
	for (int i = 0; i < counter; i++)
	{
		int value;
		input >> value;
		stack.pushBackTheElement(value);
	}
	return input;
}

IntStack& operator+=(IntStack& stack, int value)
{
	stack.pushBackTheElement(value);
	return stack;
}

IntStack& operator+=(IntStack& stack1, const IntStack& stack2)
{
	int counter = stack2._counterOfElements;
	for (int i = 0; i < counter; i++)
	{
		stack1.pushBackTheElement(stack2._stack[i]);
	}
	return stack1;
}

IntStack& IntStack::operator=(const IntStack& stack)
{
	if (&stack == this)
		return *this;
	delete[] _stack;
	_size = stack._size;
	_counterOfElements = stack._counterOfElements;
	_stack = new int[_size];
	for (int i = 0; i < _counterOfElements; i++)
	{
		_stack[i] = stack._stack[i];
	}
	return *this;
}

IntStack& IntStack::operator=(IntStack&& stack)
{
	if (&stack == this)
		return *this;
	delete[] _stack;
	_stack = stack._stack;
	_size = stack._size;
	_counterOfElements = stack._counterOfElements;
	stack._stack = nullptr;
	stack._size = 0;
	stack._counterOfElements = 0;
	return *this;
}

IntStack::~IntStack()
{
	delete[]_stack;
};
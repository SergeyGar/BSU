#pragma once
class IntStack
{
private:
	int* _stack;
	int _size;
	int _counterOfElements;
	void changeLengthOfStack(int);
public:
	IntStack();
	IntStack(int);
	IntStack(const IntStack&);
	IntStack(IntStack&&);
	~IntStack();

	bool isEmpty();
	int numberOfElements();
	int takeTheTopElement();
	void pushBackTheElement(int);
	void popBackElement();
	IntStack& getStack();
	friend std::ostream& operator<< (std::ostream&, const IntStack&);
	friend std::istream& operator>>(std::istream&, IntStack&);
	friend IntStack& operator+=(IntStack&, int);
	friend IntStack& operator+=(IntStack&, const IntStack&);
	IntStack& operator=(const IntStack&);
	IntStack& operator=(IntStack&&);
};
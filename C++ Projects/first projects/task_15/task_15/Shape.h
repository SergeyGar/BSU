#pragma once
#include <iostream>

class Drawing;

class Shape
{
private:
	Drawing* _drawing;
protected:
	Shape(Drawing*);
	~Shape();
	void drawCircle(int, int, int);
	void drawLine(int, int, int, int);
public:
	virtual void draw(int, int) = 0;
};

class Circle :public Shape
{
private:
	int _radius;
public:
	Circle(Drawing*);
	Circle(Drawing*,int);
	~Circle();
	void draw(int, int)override;
	void setRadius(int);
};

class Rectangle :public Shape
{
private:
	int _length;
	int _width;
public:
	Rectangle(Drawing*);
	Rectangle(Drawing*,int, int);
	void draw(int, int)override;
	void setLengthAndWidth(int, int);
	~Rectangle();
};

class Drawing
{
public:
	virtual void drawCircle(int, int, int) = 0;
	virtual void drawLine(int, int, int, int) = 0;
};

class GS1Drawing :public Drawing
{
	void drawCircle(int, int, int)override;
	void drawLine(int, int, int, int)override;
};

class GS2Drawing :public Drawing
{
	void drawCircle(int, int, int)override;
	void drawLine(int, int, int, int)override;
};

class GS1
{
public:
	static void draw_a_line(int, int, int, int);
	static void draw_a_circle(int, int, int);
	friend GS1Drawing;
};

class GS2
{
	static void draw_a_line(std::pair<int, int>, std::pair<int, int>);
	static void draw_a_circle(std::pair<int, int>, int);
	friend GS2Drawing;
};
#include "Shape.h"

Shape::Shape(Drawing* drawing)
{
	_drawing = drawing;
	//std::cout << "Create Shape" << std::endl;
}

Shape::~Shape()
{
	//std::cout << "Delete Shape" << std::endl << std::endl;
}

void Shape::drawCircle(int x, int y, int r)
{
	//std::cout << "Shape::DrawCircle" << std::endl;
	_drawing->drawCircle(x, y, r);
}

void Shape::drawLine(int x1, int y1, int x2, int y2)
{
	_drawing->drawLine(x1, y1, x2, y2);
}

Circle::Circle(Drawing* drawing):Shape(drawing)
{
	_radius = 0;
	//std::cout << "Create Circle" << std::endl;
}

Circle::Circle(Drawing* drawing, int r):Shape(drawing)
{
	_radius = r;
	//std::cout << "Create Circle with parametrs" << std::endl;
}

Circle::~Circle()
{
	//std::cout << "Delete Circle" << std::endl;
}

void Circle::draw(int x, int y)
{
	//std::cout << "Circle::draw" << std::endl;
	drawCircle(x, y, _radius);
}

void Circle::setRadius(int r)
{
	//std::cout << "Circle::setRadius" << std::endl;
	_radius = r;
}

Rectangle::Rectangle(Drawing* drawing) :Shape(drawing)
{
	//std::cout << "Create Rectangle" << std::endl;
	_length = 0;
	_width = 0;
}

Rectangle::Rectangle(Drawing* drawing, int length, int width) :Shape(drawing)
{
	//std::cout << "Create Rectangle with parametrs" << std::endl;
	_length = length;
	_width = width;
}

void Rectangle::draw(int x, int y)
{
	//std::cout << "Rectangle::draw" << std::endl;
	drawLine(x - _length / 2, y + _width / 2, x + _length / 2, y + _width / 2);
	drawLine(x + _length / 2, y + _width / 2, x + _length / 2, y - _width / 2);
	drawLine(x + _length / 2, y - _width / 2, x - _length / 2, y - _width / 2);
	drawLine(x - _length / 2, y - _width / 2, x - _length / 2, y + _width / 2);
}

void Rectangle::setLengthAndWidth(int length, int width)
{
	//std::cout << "Rectangle::setLengthAndWidth" << std::endl;
	_length = length;
	_width = width;
}

Rectangle::~Rectangle()
{
	//std::cout << "Delete Rectangle" << std::endl << std::endl;
}

void GS1Drawing::drawCircle(int x, int y, int r)
{
	//std::cout << "GS1Drawing::DrawCircle" << std::endl;
	GS1::draw_a_circle(x, y, r);
}

void GS1Drawing::drawLine(int x1, int y1, int x2, int y2)
{
	GS1::draw_a_line(x1, y1, x2, y2);
}

void GS2Drawing::drawCircle(int x, int y, int r)
{
	GS2::draw_a_circle(std::make_pair(x, y), r);
}

void GS2Drawing::drawLine(int x1, int y1, int x2, int y2)
{
	GS2::draw_a_line(std::make_pair(x1, y1), std::make_pair(x2, y2));
}

void GS1::draw_a_circle(int x, int y, int r)
{
	std::cout << "GS1::draw_a_circle" << std::endl
		<< "Draw a circle with coordinates: (" << x << ',' << y << ')' << " and R=" << r << std::endl;
}

void GS1::draw_a_line(int x1, int y1, int x2, int y2)
{
	std::cout << "GS1::draw_a_line" << std::endl
		<< "Draw a line with coordinates: (" << x1 << ',' << y1 << ") (" << x2 << ',' << y2 << ')' << std::endl;
}

void GS2::draw_a_circle(std::pair<int, int> point, int r)
{
	std::cout << "GS2::draw_a_circle" << std::endl
		<< "Draw a circle with coordinates: (" << point.first << ',' << point.second << ')' << " and R=" << r << std::endl;
}

void GS2::draw_a_line(std::pair<int, int> point1, std::pair<int, int> point2)
{
	std::cout << "GS2::draw_a_line" << std::endl
		<< "Draw a line with coordinates: (" << point1.first << ',' << point1.second << ") (" << point2.first << ',' << point2.second << ')' << std::endl;
}
#include <iostream>
#include "Shape.h"

int main()
{
	Drawing* drawing1;
	Drawing* drawing2;
	GS1Drawing gs1drawing;
	drawing1 = &gs1drawing;
	GS2Drawing gs2drawing;
	drawing2 = &gs2drawing;
	Circle circle(drawing1, 100);
	circle.setRadius(50);
	circle.draw(100, 100);
	Rectangle rectangle(drawing2, 50, 50);
	rectangle.draw(60, 60);
	circle.draw(40, 70);
	return 0;
}
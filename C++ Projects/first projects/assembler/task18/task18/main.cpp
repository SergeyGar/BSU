#include <iostream>
#include <iomanip>

double summ(int n, double x, double y, double z)
{
	//double productOf_xy;  //храним для того, чтобы каждый раз не возводить в степень
	//double currentnumber;
	//double summOfiElements;  //сумма слагаемых одного сомножителя
	double result;

	_asm
	{
		finit
		fld1//rez
		fldz//sum
		fld1//a_i
		fld1//xy

		mov ecx, 1
		COUNT:cmp ecx, n
		jg END

		fmul x
		fmul y
		
		fxch
		fmul st, st(1)
		fdiv z

		fadd st(2), st
		fxch st(2)

		fmul st(3), st
		
		fxch st(2)
		fxch

		inc ecx
		jmp COUNT
		END : fxch st(3)
		fst result
		finit
	}
	return result;
}

int main()
{
	std::cout << std::setprecision(16) << summ(2, 2, 2, 2);
	return 0;
}
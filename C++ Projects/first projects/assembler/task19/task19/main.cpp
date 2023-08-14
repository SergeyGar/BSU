#include <iostream>

void printThreeNumbersWithMaxProduct()
{
	size_t numberOfElements;
	std::cout << "Enter number of elements: ";
	std::cin >> numberOfElements;
	int* arrayOfInts = new int[numberOfElements];
	std::cout << "Enter " << numberOfElements << " numbers of array:" << std::endl;
	for (int i = 0; i < numberOfElements; i++)
	{
		int n;
		std::cin >> n;
		arrayOfInts[i] = n;
	}
	int theSmallestElement1;
	int theSmallestElement2;
	int theBiggestElement1;
	int theBiggestElement2;
	int theBiggestElement3;

	_asm
	{
		mov ebx, arrayOfInts
		mov eax, [ebx]; a1
		mov edx, [ebx + 4]; a2
		mov ecx, [ebx + 8]; a3

		; find max
		cmp eax, edx
		jge CompA1AndA3; if a1 >= a2
		cmp edx, ecx
		jge SetA2; if a2 >= a3

		mov esi, eax
		mov eax, ecx
		mov ecx, esi
		jmp FindOther

		SetA2 : mov esi, eax
		mov eax, edx
		mov edx, esi
		jmp FindOther

		CompA1AndA3 : cmp eax, ecx
		jge FindOther; if a1 >= a3
		mov esi, eax
		mov eax, ecx
		mov ecx, esi

		FindOther : cmp edx, ecx
		jge SetAllValues
		mov esi, edx
		mov edx, ecx
		mov ecx, esi

		SetAllValues : mov theBiggestElement1, eax
		mov theBiggestElement2, edx
		mov theBiggestElement3, ecx
		mov theSmallestElement2, edx
		mov theSmallestElement1, ecx

		mov ecx, 3
		CHECKOTHER:cmp ecx, numberOfElements
		je PrintNumbers
		mov eax, [ebx + ecx * 4]

		cmp theSmallestElement2, eax
		jl CompareTheBiggestElements
		cmp theSmallestElement1, eax
		jg SetSmallestElements
		mov theSmallestElement2, eax
		jmp CompareTheBiggestElements
		SetSmallestElements : mov edx, theSmallestElement1
		mov theSmallestElement2, edx
		mov theSmallestElement1, eax

		CompareTheBiggestElements : cmp theBiggestElement3, eax
		jg ReturnToLoop
		cmp theBiggestElement2, eax
		jg ChangeTheBiggest3
		cmp theBiggestElement1, eax
		jg ChangeTheBiggest2AndThebiggest3
		mov edx, theBiggestElement2
		mov theBiggestElement3, edx
		mov edx, theBiggestElement1
		mov theBiggestElement2, edx
		mov theBiggestElement1, eax
		jmp ReturnToLoop
		ChangeTheBiggest2AndThebiggest3 : mov edx, theBiggestElement2
		mov theBiggestElement3, edx
		mov theBiggestElement2, eax
		jmp ReturnToLoop
		ChangeTheBiggest3 : mov theBiggestElement3, eax
		ReturnToLoop : inc ecx
		jmp CHECKOTHER

		PrintNumbers : mov eax, theSmallestElement1
		imul eax, theSmallestElement2
		imul theBiggestElement1
		mov ebx, eax

		mov eax, theBiggestElement2
		imul eax, theBiggestElement3
		imul theBiggestElement1

		cmp eax,ebx
		jge printTheBiggestElements
	}
	std::cout << theSmallestElement1 << ' ' << theSmallestElement2 << ' ' << theBiggestElement1 << std::endl;
	_asm
	{
		jmp End
		printTheBiggestElements:
	}
	std::cout << theBiggestElement1 << ' ' << theBiggestElement2 << ' ' << theBiggestElement3 << std::endl;
	_asm
	{
		End:
	}
	delete[]arrayOfInts;
}

int main()
{
	printThreeNumbersWithMaxProduct();
	return 0;
}
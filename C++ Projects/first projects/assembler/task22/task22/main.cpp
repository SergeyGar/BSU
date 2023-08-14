#include<iostream>

void swapDiagonals()
{
    int n, m;
    std::cin >> n >> m;
    int** arr = new int* [n];
    for (int i = 0; i < n; i++)
    {
        arr[i] = new int[m];
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            int temp;
            std::cin >> temp;
            arr[i][j] = temp;
        }
    }
    std::cout << '\n';
    for (int i = 0; i < n; i++) 
    {
        for (int j = 0; j < m; j++) 
        {
            std::cout << arr[i][j] << ' ';
        }
        std::cout << '\n';
    }
    int sumOfUpperTriangle;

    _asm 
    {
        mov ebx, arr
        mov edx,0
        mov eax,0

        LoopForRows: cmp edx,n
        je End
        mov ecx,0
        mov edi, [ebx+4*edx]
        LoopForColumns: cmp ecx,m
        je ReturnToFirstLoop

        cmp edx,ecx
        jg Skip
        add eax,[edi+4*ecx]
        Skip:inc ecx
        jmp LoopForColumns
        ReturnToFirstLoop:inc edx

        jmp LoopForRows

        End:
        mov sumOfUpperTriangle,eax
    }
    for (int i = 0; i < n; i++)
    {
        delete arr[i];
    }
    delete[] arr;
    std::cout << std::endl;
    std::cout << sumOfUpperTriangle;
}

int main()
{
    swapDiagonals();
    return 0;
}
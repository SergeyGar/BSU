#include "myWindow.h"

MyWindow::MyWindow(LPCTSTR windowName, HINSTANCE hInst, int cmdShow,
    LRESULT(WINAPI* pWndProc)(HWND, UINT, WPARAM, LPARAM),
    LPCTSTR menuName, int x, int y, int width, int height,
    UINT classStyle, DWORD windowStyle, HWND hParent) {
    char szClassName[] = "KWndClass";
    wc.cbSize = sizeof(wc);
    wc.style = classStyle;
    wc.lpfnWndProc = pWndProc;
    wc.cbClsExtra = 0;
    wc.cbWndExtra = 0;
    wc.hInstance = hInst;
    wc.hIcon = LoadIcon(NULL, IDI_APPLICATION);
    wc.hCursor = LoadCursor(NULL, IDC_ARROW);
    wc.hbrBackground = (HBRUSH)GetStockObject(WHITE_BRUSH);
    wc.lpszMenuName = menuName;
    wc.lpszClassName = szClassName;
    wc.hIconSm = LoadIcon(NULL, IDI_APPLICATION);
    if (!RegisterClassEx(&wc)) {
        char msg[100] = "Cannot register class: ";
        strcat_s(msg, szClassName);
        MessageBox(NULL, msg, "Error", MB_OK);
        return;
    }
    hwnd = CreateWindow(szClassName, windowName, windowStyle, x, y, width, height, hParent, (HMENU)NULL, hInst, NULL);
    if (!hwnd) {
        char text[100] = "Cannot create window: ";
        strcat_s(text, windowName);
        MessageBox(NULL, text, "Error", MB_OK);
        return;
    }
    ShowWindow(hwnd, cmdShow);
    UpdateWindow(hwnd);
}
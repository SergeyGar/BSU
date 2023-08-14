#include <windows.h>
#include <windowsx.h>
#include <commctrl.h>

#include <iostream>
#include <string>

#include "MyWindow.h"

#define WIN

#ifdef WIN

#define UPDATE_LISTS WM_USER + 1

HINSTANCE instance_handle;

LRESULT CALLBACK WndProc(HWND hWnd, UINT msg, WPARAM wParam, LPARAM lParam)
{

	static HWND firstTextField;
	static HWND firstPushBackButton;
	static HWND firstPopBackButton;
	static HWND firstPushFrontButton;
	static HWND firstPopFrontButton;
	static HWND firstClearButton;
	static HWND firstMergeButton;
	static HWND firstAverageButton;
	static HWND firstAverageTextField;

	static HWND secondTextField;
	static HWND secondPushBackButton;
	static HWND secondPopBackButton;
	static HWND secondPushFrontButton;
	static HWND secondPopFrontButton;
	static HWND secondClearButton;
	static HWND secondMergeButton;
	static HWND secondAverageButton;
	static HWND secondAverageTextField;

	static HWND firstFieldWithNumberToPush;
	static HWND secondFieldWithNumberToPush;

	static HWND swapButton;
	char editValue[256];

	switch (msg)
	{
	case WM_CREATE:
		firstTextField = CreateWindowEx(WS_EX_CLIENTEDGE, "listbox", "", WS_CHILD | WS_VISIBLE | WS_VSCROLL | ES_AUTOVSCROLL, 20, 20, 260, 400, hWnd, (HMENU)105, NULL, nullptr);
		firstPushBackButton = CreateWindow("button", "PushBack", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 20, 480, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		firstPopBackButton = CreateWindow("button", "PopBack", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 113, 480, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		firstClearButton = CreateWindow("button", "Clear", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 202, 480, 75, 25, hWnd, nullptr, instance_handle, nullptr);

		firstPushFrontButton = CreateWindow("button", "PushFront", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 20, 515, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		firstPopFrontButton = CreateWindow("button", "PopFront", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 113, 515, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		firstAverageButton = CreateWindow("button", "Average", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 202, 515, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		firstMergeButton = CreateWindow("button", "1+=2", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 300, 150, 75, 25, hWnd, nullptr, instance_handle, nullptr);


		secondTextField = CreateWindowEx(WS_EX_CLIENTEDGE, "listbox", "", WS_CHILD | WS_VISIBLE | WS_VSCROLL | ES_AUTOVSCROLL, 395, 20, 260, 400, hWnd, (HMENU)106, NULL, nullptr);
		secondPushBackButton = CreateWindow("button", "PushBack", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 395, 480, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		secondPopBackButton = CreateWindow("button", "PopBack", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 488, 480, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		secondClearButton = CreateWindow("button", "Clear", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 577, 480, 75, 25, hWnd, nullptr, instance_handle, nullptr);

		secondPushFrontButton = CreateWindow("button", "PushFront", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 395, 515, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		secondPopFrontButton = CreateWindow("button", "PopFront", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 488, 515, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		secondAverageButton = CreateWindow("button", "Average", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 577, 515, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		secondMergeButton = CreateWindow("button", "2+=1", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 300, 185, 75, 25, hWnd, nullptr, instance_handle, nullptr);


		firstFieldWithNumberToPush = CreateWindow("edit", "0", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_RIGHT, 20, 430, 120, 25, hWnd, 0, instance_handle, nullptr);
		secondFieldWithNumberToPush = CreateWindow("edit", "0", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_RIGHT, 395, 430, 120, 25, hWnd, 0, instance_handle, nullptr);


		firstAverageTextField = CreateWindowEx(WS_EX_CLIENTEDGE, "listbox", "", WS_CHILD | WS_VISIBLE | WS_VSCROLL | ES_AUTOVSCROLL, 160, 430, 120, 35, hWnd, (HMENU)107, NULL, nullptr);
		secondAverageTextField = CreateWindowEx(WS_EX_CLIENTEDGE, "listbox", "", WS_CHILD | WS_VISIBLE, 535, 430, 120, 35, hWnd, (HMENU)108, NULL, nullptr);

		swapButton = CreateWindow("button", "Swap", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 300, 220, 75, 25, hWnd, nullptr, instance_handle, nullptr);

		return 0;

	case WM_COMMAND:
		SendMessage(hWnd, UPDATE_LISTS, NULL, NULL);
		return 0;

	case UPDATE_LISTS:
		/*SendDlgItemMessage(hWnd, 105, LB_RESETCONTENT, NULL, NULL);
		SendDlgItemMessage(hWnd, 106, LB_RESETCONTENT, NULL, NULL);

		for (firstIterator->first(); !firstIterator->reachedTheEnd(); firstIterator->next()) {
			std::string value = std::to_string(firstIterator->element());
			SendDlgItemMessage(hWnd, 105, LB_INSERTSTRING, -1, (LPARAM)value.data());
		}
		for (secondIterator->first(); !secondIterator->reachedTheEnd(); secondIterator->next()) {
			std::string value = std::to_string(secondIterator->element());
			SendDlgItemMessage(hWnd, 106, LB_INSERTSTRING, -1, (LPARAM)value.data());
		}*/
		/*firstView.update();
		secondView.update();*/
		return 0;
	case WM_DESTROY:
		PostQuitMessage(0);
		return 0;

	default:
		return DefWindowProc(hWnd, msg, wParam, lParam);
	}
}

int WINAPI WinMain(HINSTANCE hInst, HINSTANCE, LPSTR, int cmdShow) {
	instance_handle = hInst;
	MyWindow mywin("List", hInst, cmdShow, WndProc, NULL, 300, 0, 690, 600);
	MSG msg;
	while (GetMessage(&msg, NULL, 0, 0)) {
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}
	return (msg.wParam);
}

#endif

int main() {


	return 0;
}
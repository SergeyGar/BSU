#include <windows.h>
#include <windowsx.h>
#include <commctrl.h>

#include <iostream>
#include <string>

#include "myWindow.h"
#include "MVC.h"

#define UPDATE_LISTS WM_USER + 1

HINSTANCE instance_handle;

//Model modelForFirstList;
//Model modelForSecondLIst;
//
//View firstView(&modelForFirstList, 105);
//Controller firstController(&modelForFirstList, &firstView);
//
//View secondView(&modelForSecondLIst, 106);
//Controller secondController(&modelForSecondLIst, &secondView);

Model* modelForFirstList = new Model;

View* firstView = new View(modelForFirstList, 105);
Controller firstController(modelForFirstList, firstView);

VisitorForContainer visitor;

LRESULT CALLBACK WndProc(HWND hWnd, UINT msg, WPARAM wParam, LPARAM lParam)
{

	static HWND firstTextField;
	static HWND firstAddButton;
	static HWND firstPopBackButton;
	static HWND firstPushFrontButton;
	static HWND firstPopFrontButton;
	static HWND firstClearButton;
	static HWND firstMergeButton;
	static HWND firstCapasityButton;
	static HWND firstAverageTextField;

	static HWND firstFieldWithNumberToPush;
	char editValue[256];

	switch (msg)
	{
	case WM_CREATE:
		firstTextField = CreateWindowEx(WS_EX_CLIENTEDGE, "listbox", "", WS_CHILD | WS_VISIBLE | WS_VSCROLL | ES_AUTOVSCROLL, 20, 20, 260, 400, hWnd, (HMENU)105, NULL, nullptr);
		firstAddButton = CreateWindow("button", "add", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 20, 480, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		firstPopBackButton = CreateWindow("button", "Pop", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 113, 480, 75, 25, hWnd, nullptr, instance_handle, nullptr);
		firstClearButton = CreateWindow("button", "Clear", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 202, 480, 75, 25, hWnd, nullptr, instance_handle, nullptr);

		firstCapasityButton = CreateWindow("button", "Cardinality", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 202, 515, 75, 25, hWnd, nullptr, instance_handle, nullptr);

		firstFieldWithNumberToPush = CreateWindow("edit", "0", WS_CHILD | WS_VISIBLE | WS_BORDER | ES_RIGHT, 20, 430, 120, 25, hWnd, 0, instance_handle, nullptr);

		firstAverageTextField = CreateWindowEx(WS_EX_CLIENTEDGE, "listbox", "", WS_CHILD | WS_VISIBLE | WS_VSCROLL | ES_AUTOVSCROLL, 160, 430, 120, 35, hWnd, (HMENU)107, NULL, nullptr);
		return 0;

	case WM_COMMAND:
		if (reinterpret_cast<HWND>(lParam) == firstAddButton) {
			firstController.addElement(firstFieldWithNumberToPush);
		}
		else if (reinterpret_cast<HWND>(lParam) == firstPopBackButton)
			firstController.pop(firstFieldWithNumberToPush);
		else if (reinterpret_cast<HWND>(lParam) == firstClearButton)
			firstController.clear();
		else if (reinterpret_cast<HWND>(lParam) == firstCapasityButton) {
			firstController.accept(visitor);
			SendDlgItemMessage(hWnd, 107, LB_RESETCONTENT, NULL, NULL);
			SendDlgItemMessage(hWnd, 107, LB_INSERTSTRING, -1, (LPARAM)std::to_string(visitor.getCardinality()).data());
		}
		else
			return DefWindowProc(hWnd, msg, wParam, lParam);
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
		firstController.update(hWnd);
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

int main() {

	return 0;
}
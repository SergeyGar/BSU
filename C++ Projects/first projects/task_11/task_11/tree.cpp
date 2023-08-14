#include <iostream>
#include "tree.h"

Tree::Tree():root(nullptr){}

Tree::~Tree()
{
	deleteTree(root);
}

void Tree::addNode(TreeNode* node, int value)
{
	if (node->value == value)
		return;
	if (node->value > value)
	{
		if (node->left)
			addNode(node->left, value);
		else
			node->left = new TreeNode(value, nullptr, nullptr);
	}
	else if (node->value < value)
	{
		if (node->right)
			addNode(node->right, value);
		else
			node->right = new TreeNode(value, nullptr, nullptr);
	}
}

void Tree::addElement(int value)
{
	if (!root)
		root = new TreeNode(value, nullptr, nullptr);
	else
		addNode(root, value);
}

Tree::TreeNode* Tree::findElement(int value)
{
	return findElem(root, value);
}

Tree::TreeNode* Tree::findElem(TreeNode* node, int value)
{
	if (!node)
		return 0;
	if (node->value == value)
		return node;
	if (node->value > value)
		findElem(node->left, value);
	else
		findElem(node->right, value);
}

Tree::TreeNode* Tree::findPrevious(TreeNode* previousNode, int currentValue)
{
	if (!previousNode)
		return nullptr;
	if (previousNode->left->value == currentValue || previousNode->right->value == currentValue)
	{
		return previousNode;
	}
	if (previousNode->value > currentValue)
		findPrevious(previousNode->left, currentValue);
	else if (previousNode->value < currentValue)
		findPrevious(previousNode->right, currentValue);
}

void Tree::eraseElement(int value)
{
	if (!root)
		return;
	if (value == root->value)
	{
		eraseRoot();
	}
	else
	{
		auto previous = findPrevious(root, value);
		if (previous->left->value == value)
			eraseElement(previous->left, previous);
		else if (previous->right->value == value)
			eraseElement(previous->right, previous);
	}
	return;
}

void Tree::eraseElement(TreeNode* currentNode,TreeNode* previous)
{
	if (!currentNode)
		return;
	if (!currentNode->left && !currentNode->right)
	{
		if (previous->left==currentNode)
		{
			previous->left = nullptr;
		}
		else if(previous->right==currentNode)
		{
			previous->right = nullptr;
		}
	}
	if (currentNode->left && !currentNode->right)
	{
		if (previous->left == currentNode)
		{
			previous->left = currentNode->left;
		}
		else if (previous->right == currentNode)
		{
			previous->right = currentNode->left;
		}
	}
	if (currentNode->right && !currentNode->left)
	{
		if (previous->left == currentNode)
		{
			previous->left = currentNode->right;
		}
		else if (previous->right == currentNode)
		{
			previous->right = currentNode->right;
		}
	}
	if (currentNode->right && currentNode->left)
	{
		getLessElement(currentNode);
		return;
	}
	delete currentNode;
	return;
}

void Tree::getLessElement(TreeNode* node)
{
	auto tempnode = node->right;
	while (tempnode->left)
	{
		tempnode = tempnode->left;
	}
	auto previous = findPrevious(root, tempnode->value);
	node->value = tempnode->value;
	if (previous->value != node->value)
		previous->left = nullptr;
	else if (previous->value == node->value)
		node->right = nullptr;
	delete tempnode;
	return;
}

void Tree::eraseRoot()
{
	if (!root->left && !root->right)
	{
		root = nullptr;
	}
	else if (!root->right && root->left)
	{
		auto tempRoot = root->left;
		delete root;
		root = tempRoot;
	}
	else if (root->right && !root->left)
	{
		auto tempRoot = root->right;
		delete root;
		root = tempRoot;
	}
	else if (root->right && root->left)
	{
		getLessElement(root);
	}
	return;
}

void Tree::printTree(TreeNode* node) const
{
	if (!node)
		return;
	printTree(node->left);
	std::cout << node->value << ' ';
	printTree(node->right);
	return;
}

int Tree::countNumberOfLeafs(TreeNode* node, int& counter)
{
	if (!node)
		return counter;
	if (!node->left && !node->right)
		counter++;
	countNumberOfLeafs(node->left, counter);
	countNumberOfLeafs(node->right, counter);
	return counter;
}

int Tree::countNumberOfLeafs()
{
	int counter = 0;
	return countNumberOfLeafs(root, counter);
}

void Tree::print()
{
	printTree(root);
}

void Tree::deleteTree(TreeNode* node)
{
	if (!node)
		return;
	deleteTree(node->left);
	deleteTree(node->right);
	delete node;
}
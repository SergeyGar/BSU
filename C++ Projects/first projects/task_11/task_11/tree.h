#pragma once
class Tree
{
private:
	struct TreeNode
	{
		TreeNode(int value, TreeNode* left, TreeNode* right) :value(value), left(left), right(right) {}
		int value;
		TreeNode* left;
		TreeNode* right;
	};
	TreeNode* root;
	void addNode(TreeNode*, int);
	TreeNode* findElem(TreeNode*, int);
	void printTree(TreeNode*)const;
	TreeNode* findPrevious(TreeNode*, int);
	void getLessElement(TreeNode*);
	int countNumberOfLeafs(TreeNode*, int&);
	void eraseElement(TreeNode*, TreeNode*);
	void eraseRoot();
public:
	Tree();
	~Tree();

	int countNumberOfLeafs();
	void eraseElement(int);
	void deleteTree(TreeNode*);
	void addElement(int);
	TreeNode* findElement(int);
	void print();
};
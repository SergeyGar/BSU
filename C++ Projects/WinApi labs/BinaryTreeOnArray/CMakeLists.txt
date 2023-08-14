cmake_minimum_required(VERSION 3.24)
project(BinaryTreeOnArray)

set(CMAKE_CXX_STANDARD 14)

include_directories(BinaryTreeOnArray)

add_executable(BinaryTreeOnArray
        BinaryTreeOnArray/BinaryTree.cpp
        BinaryTreeOnArray/BinaryTree.h
        BinaryTreeOnArray/main.cpp
        BinaryTreeOnArray/MyWindow.cpp
        BinaryTreeOnArray/MyWindow.h)

#include "GameTable.h"

#include <iostream>
#include <vector>
#include <algorithm>
#include <ctime>
#include <QGridLayout>
#include <QPalette>

GameTable::GameTable():_win(false),_winCheck(false)
{
    srand(time(NULL));
    _buttons=new Buttons;
    _buttons->connect(_buttons->_newGameButton, &QPushButton::clicked, this, &GameTable::startNewGame);

    _gridLayout=new QGridLayout;
    _gridLayout->setColumnStretch(3,0);
    this->setLayout(_gridLayout);

    createSquares();
    this->setFixedSize(600,600);
    settext();
}

bool GameTable::gameOver()
{
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            if(_mainMatrix[i][j]==0)
                return false;
            if (j+1 < 4)
                if (_mainMatrix[i][j] == _mainMatrix[i][j+1])
                    return false;
            if (i+1 < 4)
                if (_mainMatrix[i+1][j] == _mainMatrix[i][j])
                    return false;
        }
    }
    return true;
}

void GameTable::begin()
{
    generateNumber();
    generateNumber();
    settext();
}

void GameTable::startNewGame()
{
    _win=false;
    _winCheck=false;
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            _mainMatrix[i][j]=0;
        }
    }
    _buttons->_scoreNumber=0;
    _buttons->increaseScore(0);
    begin();
}

bool GameTable::win()
{
    return _win&&!_winCheck;
}

void GameTable::generateNumber()
{
    int number = rand() % 9;
    if (number < 8)
        number = 2;
    else
        number = 4;
    std::vector<std::pair<int, int>> tempv;
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            if (_mainMatrix[i][j] == 0)
                tempv.push_back(std::make_pair(i, j));
        }
    }
    std::random_shuffle(tempv.begin(), tempv.end());
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            if (tempv[0].first == i && tempv[0].second == j)
            {
                _mainMatrix[i][j] = number;
                return;
            }
        }
    }
}

void GameTable::moveUp()
{
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            for (int k = j + 1; k < 4; k++)
            {
                if (_mainMatrix[k][i] != 0)
                {
                    if (_mainMatrix[j][i] == 0)
                    {
                        _mainMatrix[j][i] = _mainMatrix[k][i];
                        _mainMatrix[k][i] = 0;
                        _isMoved = true;
                        continue;
                    }
                    if (_mainMatrix[j][i] != _mainMatrix[k][i])
                        break;
                    if (_mainMatrix[k][i] == _mainMatrix[j][i])
                    {
                        _mainMatrix[j][i] <<= 1;
                        _buttons->increaseScore(_mainMatrix[j][i]);
                        if (_mainMatrix[j][i] == 2048)
                            _win = true;
                        _mainMatrix[k][i] = 0;
                        _isMoved = true;
                        break;
                    }
                }
            }
        }
    }
}

void GameTable::moveLeft()
{
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            for (int k = j + 1; k < 4; k++)
            {
                if (_mainMatrix[i][k] != 0)
                {
                    if (_mainMatrix[i][j] == 0)
                    {
                        _mainMatrix[i][j] = _mainMatrix[i][k];
                        _mainMatrix[i][k] = 0;
                        _isMoved = true;
                        continue;
                    }
                    if (_mainMatrix[i][j] != _mainMatrix[i][k])
                        break;
                    if (_mainMatrix[i][k] == _mainMatrix[i][j])
                    {
                        _mainMatrix[i][j] <<= 1;
                        if (_mainMatrix[j][i] == 2048)
                            _win = true;
                        _buttons->increaseScore(_mainMatrix[i][j]);
                        _mainMatrix[i][k] = 0;
                        _isMoved = true;
                        break;
                    }
                }
            }
        }
    }
}

void GameTable::moveRight()
{
    for (int i = 0; i < 4; i++)
    {
        for (int j = 3; j >= 0; j--)
        {
            for (int k = j - 1; k >= 0; k--)
            {
                if (_mainMatrix[i][k] != 0)
                {
                    if (_mainMatrix[i][j] == 0)
                    {
                        _mainMatrix[i][j] = _mainMatrix[i][k];
                        _mainMatrix[i][k] = 0;
                        _isMoved = true;
                        continue;
                    }
                    if (_mainMatrix[i][j] != _mainMatrix[i][k])
                        break;
                    if (_mainMatrix[i][k] == _mainMatrix[i][j])
                    {
                        _mainMatrix[i][j] <<= 1;
                        if (_mainMatrix[j][i] == 2048)
                            _win = true;
                        _buttons->increaseScore(_mainMatrix[i][j]);
                        _mainMatrix[i][k] = 0;
                        _isMoved = true;
                        break;
                    }
                }
            }
        }
    }
}

void GameTable::moveDown()
{
    for (int i = 0; i < 4; i++)
    {
        for (int j = 3; j >= 0; j--)
        {
            for (int k = j - 1; k >= 0; k--)
            {
                if (_mainMatrix[k][i] != 0)
                {
                    if (_mainMatrix[j][i] == 0)
                    {
                        _mainMatrix[j][i] = _mainMatrix[k][i];
                        _mainMatrix[k][i] = 0;
                        _isMoved = true;
                        continue;
                    }
                    if (_mainMatrix[j][i] != _mainMatrix[k][i])
                        break;
                    if (_mainMatrix[k][i] == _mainMatrix[j][i])
                    {
                        _mainMatrix[j][i] <<= 1;
                        if (_mainMatrix[j][i] == 2048)
                            _win = true;
                        _buttons->increaseScore(_mainMatrix[j][i]);
                        _mainMatrix[k][i] = 0;
                        _isMoved = true;
                        break;
                    }
                }
            }
        }
    }
}

void GameTable::moveSquares(QKeyEvent* event)
{
    if(event->key()==Qt::Key_Up)
    {
        moveUp();
    }
    else if(event->key()==Qt::Key_Down)
    {
        moveDown();
    }
    else if(event->key()==Qt::Key_Left)
    {
        moveLeft();
    }
    else if(event->key()==Qt::Key_Right)
    {
        moveRight();
    }
}

QString GameTable::setColor(int number)
{
    switch(number)
    {
        case(0):return "white";
        case(2):return "#D8BFD8";
        case(4):return "#DDA0DD";
        case(8):return "#DA70D6";
        case(16):return "#EE82EE";
        case(32):return "#BA55D3";
        case(64):return "#8B008B";
        case(128):return "#9932CC";
        case(256):return "#8A2BE2";
        case(512):return "#6A5ACD";
        case(1024):return "#483D8B";
        case(2048):return "#4B0082";
    }
}

void GameTable::settext()
{
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            _squares[i][j]->setStyleSheet("font: bold large;font-size:40px;background-color: "+setColor(_mainMatrix[i][j]));
            if(_mainMatrix[i][j]!=0)
                _squares[i][j]->setText(QString::number(_mainMatrix[i][j]));
            else
                _squares[i][j]->setText("");
        }
    }
}

void GameTable::createSquares()
{
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            _squares[i][j]=new QLabel;
            _squares[i][j]->setAlignment(Qt::AlignCenter);
            this->layout()->addWidget(_squares[i][j]);
        }
    }
}

GameTable::~GameTable(){}

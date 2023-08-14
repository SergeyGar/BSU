#ifndef GAMETABLE_H
#define GAMETABLE_H
#include "Buttons.h"

#include <QString>
#include <QKeyEvent>

class GameTable: public QWidget
{
public slots:
    void startNewGame();
public:
    int _mainMatrix[4][4]={0};
    QLabel* _squares[4][4];
    QGridLayout* _gridLayout;
    Buttons* _buttons;
    bool _win;
    bool _isMoved;
    bool _winCheck;

    GameTable();
    ~GameTable();

    void settext();
    void createSquares();
    QString setColor(int);
    void begin();

    bool gameOver();
    bool win();
    void generateNumber();

    void moveSquares(QKeyEvent*);
    void moveUp();
    void moveLeft();
    void moveRight();
    void moveDown();
};

#endif // GAMETABLE_H

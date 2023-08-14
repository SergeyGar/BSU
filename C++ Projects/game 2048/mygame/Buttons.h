#ifndef BUTTONS_H
#define BUTTONS_H
#include <QWidget>
#include <QGridLayout>
#include <QPushButton>
#include <QLabel>

class Buttons:public QWidget
{
private:
    QHBoxLayout* _hBoxLayout;
    QVBoxLayout* _vBoxLayout;
    void setDesign();

public:
    Buttons();
    QPushButton* _newGameButton;
    QPushButton *_quitButton;
    QLabel* _score;
    int _scoreNumber;
    void increaseScore(int);
};

#endif // BUTTONS_H

#include "Buttons.h"
#include <QApplication>

Buttons::Buttons():_scoreNumber(0)
{
    _vBoxLayout=new QVBoxLayout(this);
    this->setLayout(_vBoxLayout);
    this->setFixedWidth(300);
    _vBoxLayout->setSpacing(200);

    _hBoxLayout=new QHBoxLayout(this);
    _hBoxLayout->setSpacing(5);

    _quitButton = new QPushButton(" Quit ");
    _newGameButton = new QPushButton(" New Game ");
    _score=new QLabel(" Score: 0");
    _score->setFixedSize(200,40);

    setDesign();

    _hBoxLayout->addWidget(_quitButton);
    _hBoxLayout->addWidget(_newGameButton);

    QHBoxLayout* h=new QHBoxLayout;
    h->addWidget(_score);

    _vBoxLayout->addLayout(h);
    _vBoxLayout->addLayout(_hBoxLayout);

    connect(_quitButton, &QPushButton::clicked, this, &QApplication::quit);
}

void Buttons::setDesign()
{
    QString parametres="QPushButton{background-color: #C71585; color: #FFF0F5;"
"font: large 'Inter Medium';font-size: 20px;}QPushButton::pressed{background-color: #BA55D3;}";
    _score->setFixedSize(200,70);
    _score->setStyleSheet("border-bottom:4px black; border-right: 4px black;background-color: #C71585; color: #FFF0F5;"
"font: large 'Inter Medium';font-size: 25px");
    _quitButton->setFixedSize(120,70);
    _newGameButton->setFixedSize(120,70);
    _quitButton->setStyleSheet(parametres);
    _newGameButton->setStyleSheet(parametres);
}

void Buttons::increaseScore(int number)
{
    _score->setText(" Score: "+QString::number(_scoreNumber+=number));
}


#include "mainwindow.h"
#include "ui_mainwindow.h"

#include <QKeyEvent>
#include <QMessageBox>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    _table=new GameTable;
    QHBoxLayout *layout = new QHBoxLayout;

    layout->addWidget(_table);
    layout->addWidget(_table->_buttons);

    _table->setFocusPolicy(Qt::FocusPolicy::StrongFocus);
    _table->_buttons->_newGameButton->setFocusPolicy(Qt::FocusPolicy::NoFocus);
    _table->_buttons->_quitButton->setFocusPolicy(Qt::FocusPolicy::NoFocus);
    QWidget *window = new QWidget();
    window->setLayout(layout);

    setCentralWidget(window);
    _table->begin();
}

void MainWindow::newGame()
{
    _table->startNewGame();
}

void MainWindow::keyPressEvent(QKeyEvent *event)
{
    _table->moveSquares(event);
    if(_table->_isMoved==true)
    {
        _table->generateNumber();
        _table->_isMoved=false;
    }
    _table->settext();
    if(_table->win())
    {
        QMessageBox::StandardButton winWindow=QMessageBox::question(this,"You win!","Would you like to continue?",QMessageBox::Yes|QMessageBox::No);
        if(winWindow==QMessageBox::No)
        {
            MainWindow::close();
        }
        else
        {
            _table->_win=false;
            _table->_winCheck=true;
        }
    }
    if(_table->gameOver())
    {
        QMessageBox::StandardButton gameOverWindow=QMessageBox::question(this,"Game over","Start new game?",QMessageBox::Yes|QMessageBox::No);
        if(gameOverWindow==QMessageBox::Yes)
            _table->startNewGame();
        else
          MainWindow::close();
    }
}



MainWindow::~MainWindow()
{
    delete ui;
}

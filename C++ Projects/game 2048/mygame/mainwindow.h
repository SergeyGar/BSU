#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <GameTable.h>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();
    void keyPressEvent(QKeyEvent*);

private slots:
void newGame();
private:
    Ui::MainWindow *ui;
    GameTable* _table;
};
#endif // MAINWINDOW_H

package ync.ysc3232.week7.io;

import ync.ysc3232.week7.models.Position;

public interface ClickAction extends Action {
    void execute(Position a);
}
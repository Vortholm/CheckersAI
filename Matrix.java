/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers.ai;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vortholm
 */
public class Matrix {
    class Figura {
        private int Q;
        private final int COLOR;
        private int xCoords;
        private int yCoords;
        private boolean mustEat;
        private List<Integer> moves;
    
        public Figura(int COLOR, int xCoords, int yCoords) {
            this.COLOR = COLOR;
            this.xCoords = xCoords;
            this.yCoords = yCoords;
            Q = 0;
            mustEat = false;
            moves = new ArrayList<>();
        }
    
        public boolean isQueen() {
            return Q == 1;
        }
    
        public int[] getCoords() {
            int[] r = {xCoords, yCoords};
            return r;
        }   
    
        public int getColor() {
            return COLOR;
        }
    
        public void becomesQueen() {
            Q = 1;
        }
        
        public int[] canEat(int dir) {
            if (Q == 1)
                return queenCanEat(dir, xCoords, yCoords);
            int[] r = {-1, -1, -1, -1};
            if (dir == 1)
                if (!this.equals(getFigura(xCoords+1, yCoords+1)) &&
                        getFigura(xCoords+2, yCoords+2) == null) {
                    r[0] = xCoords+1;
                    r[1] = yCoords+1;
                    r[2] = xCoords+2;
                    r[3] = yCoords+2;
                }
            else if (dir == 2)
                if (!this.equals(getFigura(xCoords+1, yCoords-1)) &&
                        getFigura(xCoords+2, yCoords-2) == null) {
                    r[0] = xCoords+1;
                    r[1] = yCoords-1;
                    r[2] = xCoords+2;
                    r[3] = yCoords-2;
                }
            else if (dir == 3)
                if (!this.equals(getFigura(xCoords-1, yCoords+1)) &&
                        getFigura(xCoords-2, yCoords+2) == null) {
                    r[0] = xCoords-1;
                    r[1] = yCoords+1;
                    r[2] = xCoords-2;
                    r[3] = yCoords+2;
                }
            else
                if (!this.equals(getFigura(xCoords-1, yCoords-1)) &&
                        getFigura(xCoords-2, yCoords-2) == null) {
                    r[0] = xCoords-1;
                    r[1] = yCoords-1;
                    r[2] = xCoords-2;
                    r[3] = yCoords-2;
                }
            
            return r;
        }
    
        public boolean canEat() {
            if (Q == 1)
                if (queenCanEat(1, xCoords, yCoords)[0] != -1 || 
                        queenCanEat(2, xCoords, yCoords)[0] != -1 ||
                        queenCanEat(3, xCoords, yCoords)[0] != -1 ||
                        queenCanEat(4, xCoords, yCoords)[0] != -1) {
                    mustEat = true;
                }
            if (canEat(1)[0] != -1 || canEat(2)[0] != -1 || 
                    canEat(3)[0] != -1 || canEat(4)[0] != -1) {
                mustEat = true;
            }
            
            return mustEat;
        }
        
        private int[] queenCanEat(int dir, int x, int y) {
            int[] r = {-1, -1, -1, -1};
            if (dir == 1) {
                if ((getFigura(x+1, y+1) != null && 
                        !this.equals(getFigura(x+1, y+1))) &&
                        getFigura(x+2, y+2) == null) {
                    r[0] = x+1;
                    r[1] = y+1;
                    r[2] = x+2;
                    r[3] = y+2;
                } else
                    return queenCanEat(dir, x+1, y+1);
            }
            
            if (dir == 2) { 
                if ((getFigura(x+1, y-1) != null && 
                        !this.equals(getFigura(x+1, y-1))) &&
                        getFigura(x+2, y-2) == null) {
                    r[0] = x+1;
                    r[1] = y-1;
                    r[2] = x+2;
                    r[3] = y-2;
                } else
                    return queenCanEat(dir, x+1, y-1);
            }
            
            if (dir == 3) {
                if ((getFigura(x-1, y+1) != null && 
                        !this.equals(getFigura(x-1, y+1))) &&
                        getFigura(x-2, y+2) == null) {
                    r[0] = x-1;
                    r[1] = y+1;
                    r[2] = x-2;
                    r[3] = y+2;
                } else
                    return queenCanEat(dir, x-1, y+1);
            }
            
            if (dir == 4) {
                if ((getFigura(x-1, y-1) != null && 
                        !this.equals(getFigura(x-1, y-1))) &&
                        getFigura(x-2, y-2) == null) {
                    r[0] = x-1;
                    r[1] = y-1;
                    r[2] = x-2;
                    r[3] = y-2;
                } else
                    return queenCanEat(dir, x-1, y-1);
            }
            return r;
        }
        
        public List<Integer> getMoves() {
            if (mustEat)
                return moves;
            
            moves.clear();
            if (Q == 1) {
                getQueenMoves(this.xCoords, this.yCoords, 0);
            } else {
                if (START == 0) {// start 0 beli dole, crni gore - start 1 obrnuto
                    if (this.COLOR == WHITE) {
                        if (getFigura(this.xCoords-1, this.yCoords-1) == null) {
                            moves.add(this.xCoords-1);
                            moves.add(this.yCoords-1);
                        }
                    
                        if (getFigura(this.xCoords-1, this.yCoords+1) == null) {
                            moves.add(this.xCoords-1);
                            moves.add(this.yCoords+1);
                        }
                    } else {
                        if (getFigura(this.xCoords+1, this.yCoords+1) == null) {
                            moves.add(this.xCoords+1);
                            moves.add(this.yCoords+1);
                        }
                    
                        if (getFigura(this.xCoords+1, this.yCoords-1) == null) {
                            moves.add(this.xCoords+1);
                            moves.add(this.yCoords-1);
                        }
                    }
                }
            }
            return moves;
        }
        
        private void getQueenMoves(int x, int y, int dir) {
            if (x > 7 || y > 7 || x < 0 || y < 0)
                return;
            if (getFigura(x, y) == null) {
                moves.add(x);
                moves.add(y);                
            } else
                if (dir != 0)
                    return;
            
            if (dir == 1 || dir == 0) {
                getQueenMoves(x+1, y+1, 1);
            }
            if (dir == 2 || dir == 0) {
                getQueenMoves(x+1, y-1, 2);
            }
            if (dir == 3 || dir == 0) {
                getQueenMoves(x-1, y+1, 3);
            }
            if (dir == 4 || dir == 0) {
                getQueenMoves(x-1, y-1, 4);
            }
            return;
        }
        
        public boolean mustEat() {
            return mustEat;
        }
        
        public boolean equals(Object o) {
            Figura f = (Figura)o;
            return this.COLOR == f.getColor();
        }
    }
    /*
     *THE END OF THE FIGURA CLASS
     */
    private Figura[][] matrix;
    private final int BLACK = 1;
    private final int WHITE = 2;
    private int W_COUNTER;
    private int B_COUNTER;
    private int START;
    private boolean MUSTEAT;
    private List<Figura> MUST_EAT;
    private List<Integer> PATH1;
    private List<Integer> PATH2;
    private List<Integer> PATH3;
    
    public Matrix(int START) {
        this.START = START;
        W_COUNTER = 12;
        B_COUNTER = 12;
        matrix = new Figura[8][8];
        
        for (int i = 0;i < 8;i++) {
            for (int j = 0;j < 8;j++) {
                if (START == 0) {
                    if (i < 3 && ((i%2 == 0 && j%2 == 1) || (i%2 == 1 && j%2 == 0)))
                        matrix[i][j] = new Figura(BLACK, i, j);
                    else if (i > 4 && ((i%2 == 1 && j%2 == 0) || (i%2 == 0 && j%2 == 1)))
                        matrix[i][j] = new Figura(WHITE, i, j);
                    else
                        matrix[i][j] = null;
                } else {
                    if (i < 3 && ((i%2 == 0 && j%2 == 1) || (i%2 == 1 && j%2 == 0)))
                        matrix[i][j] = new Figura(WHITE, i, j);
                    else if (i > 4 && ((i%2 == 1 && j%2 == 0) || (i%2 == 0 && j%2 == 1)))
                        matrix[i][j] = new Figura(BLACK, i, j);
                    else
                        matrix[i][j] = null;
                }
            }
        }
    }
    
    public Figura getFigura(int x, int y) {
        return matrix[x][y];
    }
    
    public Figura[][] getMatrix() {
        return matrix;
    }
    
    public List<Figura> mustEat(int color) {
        List<Figura> mustEat = new ArrayList<>();
        for (int i = 0;i < 8;i++) {
            for (int j = 0;j < 8;j++) {
                Figura f = getFigura(i, j);
                if (f.getColor() == color)
                    if (f.canEat())
                        mustEat.add(f);
            }
        }
        MUSTEAT = true;
        return mustEat;
    }
    
    public void bestPath() {
        Figura F1 = MUST_EAT.get(MUST_EAT.size()-1);
        Figura F2 = null;
        Figura F3 = null;
        MUST_EAT.remove(MUST_EAT.size()-1);
        if (MUST_EAT.size() > 0) {
            F2 = MUST_EAT.get(MUST_EAT.size()-1);
            MUST_EAT.remove(MUST_EAT.size()-1);
            if (MUST_EAT.size() > 0) {
                F3 = MUST_EAT.get(MUST_EAT.size()-1);
                MUST_EAT.remove(MUST_EAT.size()-1);
            }
        }
        
        PATH1 = new ArrayList<>();
        bestPath(PATH1, F1.getCoords()[0], F1.getCoords()[1], F1.getColor(), this.matrix);
        if (F2 != null) {
            PATH2 = new ArrayList<>();
            bestPath(PATH2, F2.getCoords()[0], F2.getCoords()[1], F2.getColor(), this.matrix);
        }
        if (F3 != null) {
            PATH3 = new ArrayList<>();
            bestPath(PATH3, F3.getCoords()[0], F3.getCoords()[1], F3.getColor(), this.matrix);
        }
        MUST_EAT.clear();
    }
    
    private void bestPath(List<Integer> path, int x, int y, int color, Figura[][] mat) {
        if (x > 7 || y > 7 || x < 0 || y < 0)
            return;
        int r[] = mat[x][y].canEat(1);
        
        if (r[0] != -1) {
            for (int i : r)
                path.add(i);
            mat[x][y] = null;
            mat[r[0]][r[1]] = null;
            mat[r[2]][r[3]] = new Figura(color, r[2], r[3]);
            
            bestPath(path, r[2], r[3], color, mat);
        }
    }
    
    public boolean getMustEat() {
        return MUSTEAT;
    }
}

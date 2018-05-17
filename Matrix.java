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
    private List<Integer> TPATH;
    private int PATH1S;
    private int PATH2S;
    private int PATH3S;
    
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
        TPATH = new ArrayList<>();
        bestPath(1, F1.getCoords()[0], F1.getCoords()[1], F1.getColor(), this.matrix);
        if (F2 != null) {
            PATH2 = new ArrayList<>();
            bestPath(2, F2.getCoords()[0], F2.getCoords()[1], F2.getColor(), this.matrix);
        }
        if (F3 != null) {
            PATH3 = new ArrayList<>();
            bestPath(3, F3.getCoords()[0], F3.getCoords()[1], F3.getColor(), this.matrix);
        }
        MUST_EAT.clear();
    }
    
    private void bestPath(int pNum, int x, int y, int color, Figura[][] mat) {
        if (x > 7 || y > 7 || x < 0 || y < 0)
            return;
        int[] r = mat[x][y].canEat(1);
        eatUp(r, color, pNum, mat, x, y);
        
        r = mat[x][y].canEat(2);
        eatUp(r, color, pNum, mat, x, y);
        
        r = mat[x][y].canEat(3);
        eatUp(r, color, pNum, mat, x, y);
        
        r = mat[x][y].canEat(4);
        eatUp(r, color, pNum, mat, x, y);
    }
    
    private void eatUp(int[] r, int color, int pNum, Figura[][] mat, int x, int y) {
        if (r[0] != -1) {
            for (int i : r)
                TPATH.add(i);
            
            Figura f1 = new Figura(color, x, y);
            if (mat[x][y].isQueen())
                f1.becomesQueen();
            
            mat[x][y] = null;
            
            Figura f2 = new Figura(mat[r[0]][r[1]].getColor(), r[0], r[1]);
            if (mat[r[0]][r[1]].isQueen())
                f2.becomesQueen();
            
            mat[r[0]][r[1]] = null;
            
            mat[r[2]][r[3]] = new Figura(color, r[2], r[3]);
            
            if (f1.isQueen())
                mat[r[2]][r[3]].becomesQueen();
            
            bestPath(pNum, r[2], r[3], color, mat);
            
            mat[r[2]][r[3]] = null;
            mat[r[0]][r[1]] = f2;
            mat[x][y] = f1;
            
            TPATH.remove(TPATH.size()-1);
            TPATH.remove(TPATH.size()-1);
            TPATH.remove(TPATH.size()-1);
            TPATH.remove(TPATH.size()-1);
        } else {
            if (pNum == 1) {
                if (PATH1S == 0) {
                    if (PATH1.contains(-13)) {
                        int k = 0;
                        while (PATH1.get(k) != -13) {
                            PATH1S++;
                            k++;
                        }
                    } else {
                        PATH1S = PATH1.size();
                    }
                }
                if (TPATH.size() > PATH1S) {
                    PATH1.clear();
                    PATH1S = TPATH.size();
                    TPATH.stream().forEach(e -> PATH1.add(e));
                } else if (TPATH.size() == PATH1S){
                    PATH1.add(-13);
                    TPATH.stream().forEach(e -> PATH1.add(e));
                }
            } else if (pNum == 2) {
                if (PATH2S == 0) {
                    if (PATH2.contains(-13)) {
                        int k = 0;
                        while (PATH2.get(k) != -13) {
                            PATH2S++;
                            k++;
                        }
                    } else {
                        PATH2S = PATH2.size();
                    }
                }
                if (TPATH.size() > PATH2S) {
                    PATH2.clear();
                    PATH2S = TPATH.size();
                    TPATH.stream().forEach(e -> PATH2.add(e));
                } else if (TPATH.size() == PATH2S){
                    PATH2.add(-13);
                    TPATH.stream().forEach(e -> PATH2.add(e));
                }
            } else {
                if (PATH3S == 0) {
                    if (PATH3.contains(-13)) {
                        int k = 0;
                        while (PATH3.get(k) != -13) {
                            PATH3S++;
                            k++;
                        }
                    } else {
                        PATH3S = PATH3.size();
                    }
                }
                if (TPATH.size() > PATH3S) {
                    PATH3.clear();
                    PATH3S = TPATH.size();
                    TPATH.stream().forEach(e -> PATH3.add(e));
                } else if (TPATH.size() == PATH3S){
                    PATH3.add(-13);
                    TPATH.stream().forEach(e -> PATH3.add(e));
                }
            }
        }
    }
    
    public boolean getMustEat() {
        return MUSTEAT;
    }
    
    public void AI(int color) {
        if (getMustEat()) {
            
        } else {
            int defCounter = 0;
            List<Integer> lbm = new ArrayList<>();
            for (int i = 0;i < 8;i++) {
                for (int j = 0;j < 8;j++) {
                    if (matrix[i][j].getColor() == color) {
                        List<Integer> l = matrix[i][j].getMoves();
                        if (l.size() > 0) {
                            if (lbm.size() < 1)
                                if (l.size() > 2)
                                    
                            System.out.println("light");
                        }
                    }
                }
            }
        }
    }
    
    public int bestMove(int x, int y) {
        List<Integer> p1;
        int s1 = 0;
        if (PATH1.size() > 0) {
            p1 = new ArrayList<>();
            PATH1.forEach(entity1 -> p1.add(entity1));
            s1 = PATH1S;
        }
        
        List<Integer> p2;
        int s2 = 0;
        if (PATH2.size() > 0) {
            p2 = new ArrayList<>();
            PATH2.forEach(entity2 -> p2.add(entity2));
            s1 = PATH2S;
        }
        List<Integer> p3;
        int s3 = 0;
        if (PATH3.size() > 0) {
            p3 = new ArrayList<>();
            PATH3.forEach(entity3 -> p3.add(entity3));
            s1 = PATH3S;
        }
        
        return PATH3S;//OVO PROMENITI
    }
    
    public Figura[][] cloneMatrix() {
        Figura[][] r = new Figura[8][8];
        for (int i = 0;i < 8;i++) {
            for (int j = 0;j < 8;j++) {
                r[i][j] = new Figura(matrix[i][j].getColor(), i, j);
                
                if (matrix[i][j].isQueen())
                    r[i][j].becomesQueen();
            }
        }
        return r;
    }
}

package com.setsail.tetris.entites;

import com.setsail.tetris.util.TetrisConstant;

import java.awt.*;
import java.util.Random;

public class BlockFactory {

    protected int blockPattern1[][][]={
            {
                    {1,0,0,0},
                    {1,0,0,0},
                    {1,1,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,1,0},
                    {1,0,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,0,0},
                    {0,1,0,0},
                    {0,1,0,0},
                    {0,0,0,0}
            },
            {
                    {0,0,1,0},
                    {1,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0}
            }
    };

    protected int blockPattern2[][][]={
            {
                    {0,1,0,0},
                    {0,1,0,0},
                    {1,1,0,0},
                    {0,0,0,0}
            },
            {
                    {1,0,0,0},
                    {1,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,0,0},
                    {1,0,0,0},
                    {1,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,1,0},
                    {0,0,1,0},
                    {0,0,0,0},
                    {0,0,0,0}
            }
    };

    protected int blockPattern3[][][]={
            {
                    {0,1,0,0},
                    {1,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,0,0,0},
                    {1,1,0,0},
                    {1,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,1,0},
                    {0,1,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {0,1,0,0},
                    {1,1,0,0},
                    {0,1,0,0},
                    {0,0,0,0}
            }
    };

    protected int blockPattern4[][][]={
            {
                    {1,1,0,0},
                    {1,1,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,0,0},
                    {1,1,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,0,0},
                    {1,1,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,0,0},
                    {1,1,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            }
    };

    protected int blockPattern5[][][]={
            {
                    {1,0,0,0},
                    {1,0,0,0},
                    {1,0,0,0},
                    {1,0,0,0}
            },
            {
                    {1,1,1,1},
                    {0,0,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,1,1},
                    {0,0,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,0,0,0},
                    {1,0,0,0},
                    {1,0,0,0},
                    {1,0,0,0}
            }
    };

    protected int blockPattern6[][][]={
            {
                    {1,0,0,0},
                    {1,1,0,0},
                    {0,1,0,0},
                    {0,0,0,0}
            },
            {
                    {0,1,1,0},
                    {1,1,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,0,0,0},
                    {1,1,0,0},
                    {0,1,0,0},
                    {0,0,0,0}
            },
            {
                    {0,1,1,0},
                    {1,1,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            }
    };

    protected int blockPattern7[][][]={
            {
                    {0,1,0,0},
                    {1,1,0,0},
                    {1,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,0,0},
                    {0,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {0,1,0,0},
                    {1,1,0,0},
                    {1,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,0,0},
                    {0,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0}
            }
    };
    protected Random random = new Random();

    public Block createBlock(){
        int[][][] blockPattern = null;
        int randomPattern = random.nextInt(7) + 1;
        int randomRotate = random.nextInt(4);
        switch (randomPattern){
            case 1 : blockPattern = blockPattern1;
            break;
            case 2 : blockPattern = blockPattern2;
            break;
            case 3 : blockPattern = blockPattern3;
            break;
            case 4 : blockPattern = blockPattern4;
            break;
            case 5 : blockPattern = blockPattern5;
            break;
            case 6 : blockPattern = blockPattern6;
            break;
            case 7 : blockPattern = blockPattern7;
            break;
        }
        Color color = TetrisConstant.getRandomColor();
        Block block = new Block(blockPattern, randomRotate, color);
        return block;
    }

}

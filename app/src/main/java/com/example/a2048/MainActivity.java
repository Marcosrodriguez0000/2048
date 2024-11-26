package com.example.a2048;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Logica logica;
    private GridLayout gridLayout;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logica = new Logica();
        gridLayout = findViewById(R.id.gridLayout);
        gestureDetector = new GestureDetector(this, new GestureListener());

        updateUI();
    }

    private void updateUI() {
        int[][] board = logica.getBoard();
        gridLayout.removeAllViews();
        int cellSize = gridLayout.getWidth() / 4; // Assuming the GridLayout is square
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                TextView cell = new TextView(this);
                cell.setText(board[i][j] == 0 ? "" : String.valueOf(board[i][j]));
                cell.setTextSize(32);
                cell.setGravity(android.view.Gravity.CENTER);
                cell.setBackgroundResource(getCellBackground(board[i][j]));
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = cellSize;
                params.height = cellSize;
                params.rowSpec = GridLayout.spec(i);
                params.columnSpec = GridLayout.spec(j);
                cell.setLayoutParams(params);
                gridLayout.addView(cell);
            }
        }
    }

    private int getCellBackground(int value) {
        switch (value) {
            case 2: return R.drawable.cell_2;
            case 4: return R.drawable.cell_4;
            case 8: return R.drawable.cell_8;
            case 16: return R.drawable.cell_16;
            case 32: return R.drawable.cell_32;
            case 64: return R.drawable.cell_64;
            case 128: return R.drawable.cell_128;
            case 256: return R.drawable.cell_256;
            case 512: return R.drawable.cell_512;
            case 1024: return R.drawable.cell_1024;
            case 2048: return R.drawable.cell_2048;
            default: return R.drawable.cell_default;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e2.getX() - e1.getX();
            float deltaY = e2.getY() - e1.getY();
            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                if (deltaX > 0) {
                    logica.moveRight();
                } else {
                    logica.moveLeft();
                }
            } else {
                if (deltaY > 0) {
                    logica.moveDown();
                } else {
                    logica.moveUp();
                }
            }
            updateUI();
            return true;
        }
    }
}
package com.shemul.dxball;

import android.R.bool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;



class GameCanvas extends View{
    int _count = 0;
    int _level;
    public static int _life = 1;
    public static boolean _gameOver;
    float _brickX =0, _brickY =0;
    static int _score =0;
    Canvas _canvas;
    boolean _createGame;
    Paint _paint;
    Bar _bar;
    Ball _ball;
    float _touchPoint;
    boolean _gameStart = true;
    ArrayList<Bricks> _bricks =new ArrayList<Bricks>();
    Stage _stage = new Stage();
    boolean flag = false;
    public GameCanvas(Context context) {
        super(context);
        _paint =new Paint();
        _level = 1;
        _createGame = true;
        _gameOver = false;
        _bar = new Bar();
        _ball = new Ball();

        setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				
                int _action = event.getActionMasked();
                if (_ball.isballAvailable()) {
                    _touchPoint = event.getX();
                    if (_touchPoint < v.getWidth() / 2 && _bar.getBarLeft()-20 > 0) {
                        _bar.setBarLeft(_bar.getBarLeft() - 10);
                        if(_count <1) {
                            _ball.setX(_ball.getX() - 10);
                        }
                        _touchPoint = -10;
                    } else if (_touchPoint >= v.getWidth() / 2 && _bar.getBarRight()+20 < v.getWidth()) {
                        _bar.setBarLeft(_bar.getBarLeft() + 10);
                        if(_count <1) {
                            _ball.setX(_ball.getX() + 10);
                        }
                        _touchPoint = -10;
                    }
                }

                if(_action == MotionEvent.ACTION_UP){
                    if(_count <2){
                        _count +=1;
                    }
                }
                if(flag) {
                	_createGame = true;
                	_life = 1 ;
                	_level = 1;
                	flag = false;
                }
				return true;
			}

        });
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (_gameStart) {
            _gameStart = false;
            _bar.setBar(canvas);
            _ball.setBall(canvas, _bar);
        }
        canvas.drawRGB(255, 255, 255);
        _paint.setColor(Color.BLACK);
        _paint.setStyle(Paint.Style.FILL);
        _ball.drawBall(canvas, _paint);
        _bar.drawBar(canvas, _paint);

        if(_count == 2){
            _ball.nextPos(canvas, _bar, _paint);
        }
        if(_count <= 1){
            _paint.setColor(Color.parseColor("#3F51B5"));
            _paint.setTextSize(100);
            _paint.setFakeBoldText(true);
            canvas.drawText("TAP TO START",canvas.getWidth()/2-350,canvas.getHeight()/2+100, _paint);
        }

        this._canvas = canvas;

        _brickX = canvas.getWidth() / 7;
        _brickY = (canvas.getHeight() / 15) ;

        if (_createGame) {
            _createGame = false;
            
            if (_level == 1) {
                _stage.stage_level_one(canvas, _brickX, _brickY, _bricks);
            }
            else if (_level == 2) {
                _stage.stage_level_two(canvas, _brickX, _brickY, _bricks);
            }
            else
                _gameOver = true;
        }

        for(int i = 0; i< _bricks.size(); i++){
        	
        	Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.br);

        	  
            canvas.drawRect(_bricks.get(i).getLeft(), _bricks.get(i).getTop(), _bricks.get(i).getRight(), _bricks.get(i).getBottom(), _bricks.get(i).getPaint());
        }

        _ball.ballBrickCollision(_bricks, _ball);

        if(_bricks.size() == 0){
            _count = -1;
            _level += 5;
            _createGame = true;
            _gameStart = true;
        }

        if( _count == -1 ){

                _paint.setColor(Color.BLACK);
                canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), _paint);
                _paint.setColor(Color.WHITE);
                _paint.setTextSize(50);
                _paint.setFakeBoldText(true);
                canvas.drawText("Your Score# "+ _score,20,40, _paint);

        }

        if(_ball.isballAvailable() == false){
            _ball.setBallAvailable(true);
            _count = 0;
            _gameStart = true;
            _life -= 1;
        }

        _paint.setTextSize(30);
        _paint.setFakeBoldText(true);
        canvas.drawText("Score# " + _score, 20,40, _paint);

        if(_life == 0 || _gameOver){
            _paint.setColor(Color.parseColor("#607D8B"));
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), _paint);
            _paint.setColor(Color.YELLOW);
            _paint.setTextSize(100);
            _paint.setFakeBoldText(true);
            canvas.drawText("Game Over",canvas.getWidth()/2-200,canvas.getHeight()/2+100, _paint);
            _paint.setTextSize(50);
            canvas.drawText("Your score was " + _score ,canvas.getWidth()/2-200,canvas.getHeight()/2-150, _paint);
            flag = true;
            _gameOver = false;
            _level = 0;
            
        }

        invalidate();
    }


}
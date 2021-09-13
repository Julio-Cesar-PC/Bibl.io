package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.GameObject.Order;
import com.mygdx.game.Hero.Hero;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.GameObject.Book;
import com.mygdx.game.GameObject.Bag;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.graphics.GL20;
import java.util.ArrayList;


public class GameScreen implements Screen {
	// Game
	final Biblio game;
	private int score;
	private Hero hero;
	SpriteBatch batch;
	private boolean gameover;
	final GameScreen gameScreen = this;

	// Maps
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer renderer;
	private float MapWidth;
	private float MapHeight;
	private FitViewport viewport;
	private OrthographicCamera camera;
	private MapLayers mapLayers = new MapLayers();

	// Objects
	private ArrayList<GameObject> bookshelves = new ArrayList<GameObject>();
	private GameObject counter;

	// Bag
	private Bag heroBag = new Bag();
	BitmapFont font = new BitmapFont();
	Order order;

	//HUD
	Texture bookTexture;
	Texture redBook, blueBook, yellowBook, greenBook, whiteBook, orangeBook, pinkBook;
	private ShapeRenderer rectangleHUD;

	//Timer
	private float timeSeconds = 0f;
	private float period = 120f;

	public GameScreen(final Biblio game){
		this.game = game;
		batch = new SpriteBatch();
	}


	@Override
	public void show() {
		rectangleHUD = new ShapeRenderer();
		tiledMap = new TmxMapLoader().load("level.tmx");
		renderer = new OrthogonalTiledMapRenderer(tiledMap, 1);
		MapWidth = tiledMap.getProperties().get("width", Integer.class) * tiledMap.getProperties().get("tilewidth", Integer.class);
		MapHeight = tiledMap.getProperties().get("height", Integer.class) * tiledMap.getProperties().get("tileheight", Integer.class);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 1280);
		mapLayers = tiledMap.getLayers();
		viewport = new FitViewport(640,640, camera);
		hero = new Hero(new Sprite(new Texture("boneco.png")), (TiledMapTileLayer) tiledMap.getLayers().get("Objetos"));
		hero.setPosition(640, 640);
		order = new Order();
		order.listOrder();
		Gdx.input.setInputProcessor(hero);
		//Textura dos livros
		loadBookTextures();
		//Carregar objetos
		loadObjects();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Time
		timeSeconds +=Gdx.graphics.getDeltaTime();
		if(timeSeconds > period){
			kill();
		}
		// Order
		if (order.isConcluded()) {
			order = new Order();
			order.listOrder();
		}
		// Camera
		camera.position.x = hero.getX() + hero.getWidth();
		camera.position.y = hero.getY() + hero.getHeight();
		camera.update();
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		renderer.setView(camera);
		renderer.render(new int[]{0});
		renderer.render(new int[]{1});
		renderer.render(new int[]{2});
		// Hero draw
		renderer.getBatch().begin();
		hero.draw(renderer.getBatch());
		renderer.getBatch().end();
		renderer.render(new int[]{3});
		// HUD draw
		manageBag();
		drawHUD2();
		batch.begin();
		drawOrder();
		drawHUD();
		batch.end();
		// Gameover condition
		if(gameover) {
			this.dispose();
			game.setScreen(new GameOverScreen(game, score));
		}
	}
	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}


	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	public void kill () {
		gameover = true;
	}

	public void takeBook(){
		for(GameObject object: bookshelves){
			if(object.containsHero(hero)){
				System.out.print(object.getName() + '\n' + heroBag.amountOfBooks() + '\n');
				Book temp = new Book(object.addBook());
				heroBag.addBook(temp);
				heroBag.listBooks();
			}
		}
	}

	public boolean deliverBooks() {
		if(counter.containsHero(hero)) {
			if (heroBag.getBag().size() == 5) {
				if (order.checkOrder(heroBag)) {
					heroBag.getBag().clear();
					score += 10;
					return false;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	public void loadObjects(){
		for(MapObject mapObject : tiledMap.getLayers().get("Estantes").getObjects()){
			float width = mapObject.getProperties().get("width",Float.class);
			float height = mapObject.getProperties().get("height",Float.class);
			float x = mapObject.getProperties().get("x",Float.class);
			float y = mapObject.getProperties().get("y",Float.class);

			if(mapObject.getName().equals("LocalDeEntrega")){
				counter = new GameObject(mapObject.getName(),(int)x,(int)y,(int)width,(int)height,true);
			}else{
				String bookName = mapObject.getProperties().get("Cor",String.class);
				GameObject temp = new GameObject(mapObject.getName(),(int)x,(int)y,(int)width,(int)height,bookName);
				bookshelves.add(temp);
			}
		}
	}

	public void manageBag(){
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			takeBook();
			gameover = deliverBooks();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.R)){
			if (heroBag.getBag().size() > 0)
				heroBag.removeBook();
		}
	}

	public void loadBookTextures(){
		bookTexture = new Texture("pixelBooks/book.png");
		orangeBook = new Texture("pixelBooks/LivroLaranja.png");
		blueBook = new Texture("pixelBooks/LivroAzul.png");
		whiteBook = new Texture("pixelBooks/LivroBranco.png");
		pinkBook = new Texture("pixelBooks/LivroRosa.png");
		redBook = new Texture("pixelBooks/LivroVermelho.png");
		yellowBook = new Texture("pixelBooks/LivroAmarelo.png");
		greenBook = new Texture("pixelBooks/LivroVerde.png");
	}

	public void drawOrder(){
		ArrayList<Book> orderTemp = order.getOrder();
		int size = 24, i = 0, passo = size + 10;
		for(Book book : orderTemp){
			if(book.getColor().equals("Azul")){
				batch.draw(blueBook, 10, 600-i*passo, size, size);
			}else if(book.getColor().equals("Verde")){
				batch.draw(greenBook, 10, 600-i*passo, size, size);
			}else if(book.getColor().equals("Vermelho")){
				batch.draw(redBook, 10, 600-i*passo, size, size);
			}else if(book.getColor().equals("Amarelo")){
				batch.draw(yellowBook, 10, 600-i*passo, size, size);
			}else if(book.getColor().equals("Branca")){
				batch.draw(whiteBook, 10, 600-i*passo, size, size);
			}else if(book.getColor().equals("Rosa")){
				batch.draw(pinkBook, 10, 600-i*passo, size, size);
			}else if(book.getColor().equals("Laranja")){
				batch.draw(orangeBook, 10, 600-i*passo, size, size);
			}
			i++;
		}
	}

	public void drawHUD(){
		batch.draw(bookTexture, 530, 10, 48, 48);
		font.getData().setScale(2);
		font.setColor(0.7f, 0, 1, 1);
		font.draw(batch, String.valueOf(heroBag.amountOfBooks()), camera.viewportWidth-40, 40);
		font.draw(batch,"Score: " + score, 40, 40);
		font.getData().setScale(1.5f);
		font.draw(batch, "Tempo: " + (int) timeSeconds, (camera.viewportWidth/2)-50, 40);
		font.getData().setScale(1);
		//font.setColor(1, 1, 1, 1);
		//font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), camera.viewportWidth-60, camera.viewportHeight);
	}

	public void drawHUD2(){
		rectangleHUD.begin(ShapeRenderer.ShapeType.Filled);
		rectangleHUD.setColor(0.9f, 0.9f, 0.9f, 1f);
		rectangleHUD.rect(0, 0, Gdx.graphics.getWidth(), 60);
		rectangleHUD.end();
	}
}

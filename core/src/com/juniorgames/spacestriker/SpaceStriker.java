package com.juniorgames.spacestriker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

public class SpaceStriker extends ApplicationAdapter {
	
	public PerspectiveCamera cam;
	public Model model;
	public ModelInstance modelInstance;
	public ModelBatch modelBatch;
	public Environment environment;
	public CameraInputController camController;
	public AssetManager assets;
	boolean loading;
	float z_coord = 0f;
	
	@Override
	public void create () {
		assets = new AssetManager();
		assets.load("assets/default/models/model1/mainblock.obj",Model.class);
		loading = true;
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.4f,0.4f,0.4f,1f));
		environment.add(new DirectionalLight().set(0.8f,0.8f,0.8f,-1f,-0.8f,-0.2f));
		cam = new PerspectiveCamera(67,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		cam.position.set(100f,100f,100f);
		cam.lookAt(0f, 0f, 0f);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();
	}
	public void doneLoading(){
		model = assets.get("assets/default/models/model1/mainblock.obj",Model.class);
		modelInstance = new ModelInstance(model);
		modelInstance.transform.setToTranslation(0,0,z_coord);
		loading = false;
	}
	@Override
	public void render () {
		if(loading&&assets.update()){
			doneLoading();
		}
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
		if(!loading){
		modelBatch.begin(cam);
		modelBatch.render(modelInstance,environment);
		modelBatch.end();
		}//end if
	}
	
	@Override
	public void dispose () {
		modelBatch.dispose();
		model.dispose();
	}
}

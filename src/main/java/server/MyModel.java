package server;

import java.util.List;

import adapterSokoban.SokobanSolver;
import common.Level;
import model.commands.LoadFileCommand;
import model.commands.MoveDownCommand;
import model.commands.MoveLeftCommand;
import model.commands.MoveRightCommand;
import model.commands.MoveUpCommand;
import model.commands.SaveFileCommand;
import server.model.policy.MySokobanPolicy;

public class MyModel implements Model {

	private Level level=new Level();
	private Level startLevel=new Level();
	private LoadFileCommand load= new LoadFileCommand();
	private SaveFileCommand save =new SaveFileCommand();
	private MoveUpCommand up = new MoveUpCommand(this);
	private MoveDownCommand down= new MoveDownCommand(this);
	private MoveLeftCommand left= new MoveLeftCommand(this);
	private MoveRightCommand right= new MoveRightCommand(this);
	private String filePath;
	private MySokobanPolicy msp= new MySokobanPolicy(this);






	public Level getStartLevel() {
		return startLevel;
	}

	public String getFilePath() {
		return filePath;
	}

	public MySokobanPolicy getMsp() {
		return msp;
	}

	public void setStartLevel(Level startLevel) {
		this.startLevel = startLevel;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setMsp(MySokobanPolicy msp) {
		this.msp = msp;
	}

	@Override
	public Level getLevel() {
		return this.level;
	}

	@Override
	public void setLevel(Level level) {
		this.level=level;

	}

	@Override
	public LoadFileCommand getLoad() {
		return this.load;
	}

	@Override
	public void setLoad(LoadFileCommand load) {
		this.load=load;

	}

	@Override
	public SaveFileCommand getSave() {
		return this.save;
	}

	@Override
	public void setSave(SaveFileCommand save) {
		this.save=save;

	}

	@Override
	public MoveUpCommand getUp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUp(MoveUpCommand up) {
		// TODO Auto-generated method stub

	}

	@Override
	public MoveDownCommand getDown() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDown(MoveDownCommand down) {
		// TODO Auto-generated method stub

	}

	@Override
	public MoveLeftCommand getLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLeft(MoveLeftCommand left) {
		// TODO Auto-generated method stub

	}

	@Override
	public MoveRightCommand getRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRight(MoveRightCommand right) {
		// TODO Auto-generated method stub

	}

	@Override
	public Level getcCurrentLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MySokobanPolicy getPolicy() {
		return this.msp;
	}

	@Override
	public void setPolicy(MySokobanPolicy policy) {
		this.msp=policy;

	}

	@Override
	public void moveLeft() {
		this.msp.moveLeft(this.level);

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveRight() {
		this.msp.moveRight(this.level);

	}

	@Override
	public void moveUp() {
		this.msp.moveUp(this.level);

	}

	@Override
	public void moveDown() {
		this.msp.moveDown(this.level);

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSteps() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String solve(String name) {
		StringBuilder solution=new StringBuilder();
		SokobanSolver solver=new SokobanSolver();
		solver.readLevel(name);
		List<String> sol=solver.solveSokoban();
		for(int i=0;i<sol.size();i++){
			solution.append(sol.get(i)+" ");

		}
		return solution.toString();

	}


}

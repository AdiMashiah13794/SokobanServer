package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import common.Level;
import mvvm.model.Administrator;

public class MyClientHandler implements ClientHandler {
	private BlockingQueue<String> commands;
	//String FromClientToServer[] = new String[30];
	int sizeArray = 0;
	String solution;
	private boolean stop;
	LinkedList<String> params;
	private Model model;
	boolean flag = false;
	Map<String, String> solutionsMap = new HashMap<>();

	public MyClientHandler() {
		this.commands = new ArrayBlockingQueue<String>(30);
		this.stop = false;
		commands.clear();
		this.model = new MyModel();

	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public LinkedList<String> getParams() {
		return params;
	}

	public void setParams(LinkedList<String> params) {
		this.params = params;
	}

	@Override
	public void handleClient(Socket socket, String id, InputStream inFromClient, OutputStream outToClient)
			throws IOException, InterruptedException {

		this.params = new LinkedList<String>();

		BufferedReader userInput = new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter serverOutput = new PrintWriter(outToClient);
		Administrator.getInstance().addClient(id, socket);
		// Thread fromClient=aSyncReadInput(userInput,"Exit");
		// Thread toClient=aSyncSendOutput(serverOutput);
		// fromClient.join();
		// toClient.join();
		// userInput.close();
		// serverOutput.close();
		//
		// System.out.println("handkle vlccjgug djdfhedfpogdfpoj");
		aSyncReadInput(userInput, "Exit");
		aSyncSendOutput(serverOutput);

	}

	private void aSyncSendOutput(PrintWriter out) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					sendOutPut(out);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}

	/*
	 * private Thread aSyncSendOutput(PrintWriter out){ Thread t= new Thread(new
	 * Runnable() {
	 *
	 * @Override public void run() { try { sendOutPut(out); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 *
	 * } }); t.start(); return t; }
	 */
	private void sendOutPut(PrintWriter out) throws InterruptedException {
		String str = null;
		while (!this.stop) {
			// if(this.params.size()>0){
			str = commands.poll(10, TimeUnit.SECONDS);

			if (str != null) {
				// str=this.params.removeFirst();
				System.out.println("from server to client:" + " " + str);

				if (str.equals("Move Up")) {
					System.out.println("the server just sent succeed to the client");
					out.println("succeed");
					ArrayList<String> newLevelArray = this.model.getLevel().convertFromObgToString();
					String newLine;
					for (int i = 0; i < newLevelArray.size(); i++) {
						newLine = newLevelArray.get(i);
						out.println(newLine);
						out.flush();

					}
					out.flush();

				}
				if (str.equals("Move Down")) {
					out.println("succeed");
					System.out.println("move down succsed");
					ArrayList<String> newLevelArray = this.model.getLevel().convertFromObgToString();
					String newLine;
					for (int i = 0; i < newLevelArray.size(); i++) {
						newLine = newLevelArray.get(i);
						System.out.println(newLine);
						out.println(newLine);
						out.flush();

					}
					out.flush();

				}
				if (str.equals("Move Right")) {
					out.println("succeed");
					ArrayList<String> newLevelArray = this.model.getLevel().convertFromObgToString();
					String newLine;
					for (int i = 0; i < newLevelArray.size(); i++) {
						newLine = newLevelArray.get(i);
						out.println(newLine);
						out.flush();

					}
					out.flush();

				}
				if (str.equals("Move Left")) {
					out.println("succeed");
					ArrayList<String> newLevelArray = this.model.getLevel().convertFromObgToString();
					String newLine;
					for (int i = 0; i < newLevelArray.size(); i++) {
						newLine = newLevelArray.get(i);
						out.println(newLine);
						out.flush();

					}
					out.flush();

				}
				if (str.equals("Solution")) {
					out.println("Solution");
					out.println(this.solution);
					out.flush();
				}
				str = null;

				// }
			}
		}
	}

	//public String[] getFromClientToServer() {
		//return FromClientToServer;
	//}

	public int getSizeArray() {
		return sizeArray;
	}

	//public void setFromClientToServer(String[] fromClientToServer) {
	//	FromClientToServer = fromClientToServer;
	//}

	public void setSizeArray(int sizeArray) {
		this.sizeArray = sizeArray;
	}

	private void aSyncReadInput(BufferedReader in, String exitStr) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					readInput(in, exitStr);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}

	/*
	 * private Thread aSyncReadInput(BufferedReader in,String exitStr){ Thread
	 * t= new Thread(new Runnable() {
	 *
	 * @Override public void run() { readInput(in,exitStr);
	 *
	 * } }); t.start(); return t; }
	 */
	private void readInput(BufferedReader in, String exitStr) throws InterruptedException {
		String line = null;

		try {

			while (!flag) {
				line = in.readLine();
				if (line != null) {
					System.out.println("i am in the readinput function");

					// line=in.readLine();
					System.out.println("from client to server" + " " + line);
					//this.FromClientToServer[sizeArray++] = line;

					if (line.equals(exitStr)) {
						flag = true;
						try {

							params.add(line);
							commands.put("bye");
							stop();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if (line.equals("load")) {
						System.out.println("im in load of the server from the client");
						String name = in.readLine();
						System.out.println("the name of the level is: " + name);
						String fullName = in.readLine();
						System.out.println("the path of the level is:" + fullName);

						String newLine;
						ArrayList<String> lines = new ArrayList<String>();
						newLine = in.readLine();
						while (!newLine.equals("done")) {
							lines.add(newLine);

							newLine = in.readLine();

						}
						for (int i = 0; i < lines.size(); i++) {
							System.out.println(lines.get(i));

						}
						/*
						 * for(int
						 * i=0;i<this.model.level.getelementsString().size();i++
						 * ){ newLine=in.readLine(); lines.add(newLine); }
						 */
						Level level = new Level(lines);
						Level level2 = new Level(lines);
						level2.setFilePath(fullName);
						this.model.setStartLevel(level2);
						// this.model.setStartLevel(level);
						System.out.println("the start level:" + this.model.getStartLevel());
						this.model.setLevel(level);
						this.model.getLevel().setFilePath(fullName);

						this.model.getLevel().setLevelName(name);
						for (int i = 0; i < this.model.getLevel().getPlayerNumber(); i++) {
							System.out.println(this.model.getLevel().getPlayers().get(i));

						}

					}

					else if (line.equals("Move Up")) {
						System.out.println("im in the move up command in the server");
						this.model.getPolicy().setPlayer(this.model.getLevel().getPlayers().get(0));
						this.model.getPolicy().moveUp(this.model.getLevel());

						if (this.model.getPolicy().isSucceedU() == true) {
							System.out.println("the server saw that soko can move up");
							this.commands.put("Move Up");
						}
						// this.model.getLevel().getPlayers().get(0).setPlayerImage(new
						// Image(new
						// FileInputStream("./resources/SpongeBob.png")));

					} else if (line.equals("Move Down")) {
						System.out.println("im in the move Down command in the server");
						System.out.println("print in the move down");

						this.model.getPolicy().setPlayer(this.model.getLevel().getPlayers().get(0));
						this.model.getPolicy().moveDown(this.model.getLevel());
						if (this.model.getPolicy().isSucceedD() == true) {
							System.out.println("the server saw that soko can move Down");
							this.commands.put("Move Down");
						}
						// this.model.getLevel().getPlayers().get(0).setPlayerImage(new
						// Image(new
						// FileInputStream("./resources/SpongeBob.png")));

					} else if (line.equals("Move Left")) {
						System.out.println("im in the move Left command in the server");

						this.model.getPolicy().setPlayer(this.model.getLevel().getPlayers().get(0));
						this.model.getPolicy().moveLeft(this.model.getLevel());
						if (this.model.getPolicy().isSucceedL() == true)
							this.commands.put("Move Left");
						// this.model.getLevel().getPlayers().get(0).setPlayerImage(new
						// Image(new
						// FileInputStream("./resources/SpongeBob.png")));

					} else if (line.equals("Move Right")) {
						System.out.println("im in the move Right command in the server");

						this.model.getPolicy().setPlayer(this.model.getLevel().getPlayers().get(0));
						this.model.getPolicy().moveRight(this.model.getLevel());
						if (this.model.getPolicy().isSucceedR() == true)
							this.commands.put("Move Right");
						// this.model.getLevel().getPlayers().get(0).setPlayerImage(new
						// Image(new
						// FileInputStream("./resources/SpongeBob.png")));

					} else if (line.equals("Solution")) {


						System.out.println("I am in the solution command in the server");
						String name = this.model.getLevel().getLevelName();

						if (solutionsMap.containsKey(name)) {
							this.commands.put("Solution");
							this.solution = solutionsMap.get(name);
						} else {
							String fullName = this.model.getLoad().getFilePath();
							String solution = getSolutionFromService(name);
							this.model.setLevel(this.model.getStartLevel());

							System.out.println("the solution from the database in the server:");
							System.out.println(solution);
							if (solution == null) {
								String sol = this.model.solve(this.model.getStartLevel().getFilePath());
								String splitedSolution[] = decodeSolution(sol);
								StringBuilder solutionEncode = new StringBuilder();
								for (int i = 0; i < splitedSolution.length; i++) {
									// to do -> enter the solution from strips
									// to the database
									if (splitedSolution[i] != null) {
										if (splitedSolution[i].equals("Move Up")) {
											this.model.getPolicy().setPlayer(this.model.getLevel().getPlayers().get(0));
											this.model.getPolicy().moveUp(this.model.getLevel());
											this.commands.put("Move Up");
											Thread.sleep(500);

											//this.commands.put(splitedSolution[i]);
											solutionEncode.append(encodeSolution(splitedSolution[i]));
										}
										else if (splitedSolution[i].equals("Move Down")) {
											this.model.getPolicy().setPlayer(this.model.getLevel().getPlayers().get(0));
											this.model.getPolicy().moveDown(this.model.getLevel());
											this.commands.put("Move Down");
											Thread.sleep(500);

											//this.commands.put(splitedSolution[i]);
											solutionEncode.append(encodeSolution(splitedSolution[i]));
										}
										else if (splitedSolution[i].equals("Move Right")) {
											this.model.getPolicy().setPlayer(this.model.getLevel().getPlayers().get(0));
											this.model.getPolicy().moveRight(this.model.getLevel());
											this.commands.put("Move Right");
											Thread.sleep(500);

											//this.commands.put(splitedSolution[i]);
											solutionEncode.append(encodeSolution(splitedSolution[i]));
										}
										else if (splitedSolution[i].equals("Move Left")) {
											this.model.getPolicy().setPlayer(this.model.getLevel().getPlayers().get(0));
											this.model.getPolicy().moveLeft(this.model.getLevel());
											this.commands.put("Move Left");
											Thread.sleep(500);

											//this.commands.put(splitedSolution[i]);
											solutionEncode.append(encodeSolution(splitedSolution[i]));
										}
									}
									System.out.println("the encode solution:");
									System.out.println(solutionEncode.toString());
									//addNewSolution(solutionEncode.toString());
								}
								addNewSolution(name,solutionEncode.toString());
							}
							/*
							 * else params.add(line); setChanged();
							 * notifyObservers(params);
							 */
						}
					}
					line = null;
				}
			}
		}

		catch (IOException e) {
			// TODO Auto-generated catch block

		}

	}

	private char encodeSolution(String command) {
		char c = ' ';
		if (command.equals("Move Up")) {
			c = 'u';
			return c;
		} else if (command.equals("Move Down")) {
			c = 'd';
			return c;
		} else if (command.equals("Move Left")) {
			c = 'l';
			return c;
		} else if (command.equals("Move Right")) {
			c = 'r';
			return c;
		}

		return c;
	}

	private String[] decodeSolution(String solutionEncode) {
		String[] splited = null;
		String[] commands = new String[100];
		splited = solutionEncode.split("\\s+");
		int k = 0;
		for (int i = 0; i < splited.length - 1; i = i + 2) {
			commands[k++] = splited[i] + " " + splited[i + 1];
			System.out.println("in decode");
			System.out.println(commands[i]);

		}
		return commands;
	}

	private void addNewSolution(String levelName,String encodeSolution) {
		String url = "http://localhost:8080/SokobanService5/webapi/solutions";
		Client client=ClientBuilder.newClient();
		WebTarget webTarget=client.target(url);
		Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_FORM_URLENCODED);
		Form form =new Form();
		form.param("name", levelName);
		form.param("solution", encodeSolution);
		Response response=invocationBuilder.post(Entity.form(form));

		if (response.getStatus() == 204) {
			 System.out.println("Solution added successfully");
			 }
		else
			System.out.println(response.getHeaderString("error"));




		/*
		System.out.println("the level name in add solution");
		System.out.println(levelName);
		String url =
				"http://localhost:8080/JerseyDemo/webapi/solutions";
				 Client client = ClientBuilder.newClient();
				 WebTarget webTarget = client.target(url);
				 SokobanSolution soko = new SokobanSolution(levelName, encodeSolution);

				 Invocation.Builder invocationBuilder =webTarget.request(MediaType.APPLICATION_JSON);
				 Response response =invocationBuilder.post(Entity.json(new SokobanSolution(levelName,encodeSolution)));

				 //final Invocation.Builder invocationBuilder = webTarget.request();
				  //  final Invocation invocation = invocationBuilder.buildPost(Entity.entity(new SokobanSolution(levelName,encodeSolution), MediaType.APPLICATION_JSON));

				 if (response.getStatus() == 204) {
				 System.out.println("Employee added successfully");
				 }
				 else {
				System.out.println(response.getHeaderString("errorResponse")
				);
				 }
				 }

		/*
		String url = "http://localhost:8080/JerseyDemo/webapi/solutions";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		SokobanSolution sokoSol = new SokobanSolution(levelName, encodeSolution);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.json(sokoSol));

		if (response.getStatus() == 204) {
			System.out.println("Solution added successfully");
		} else {
			System.out.println(response.getHeaderString("errorResponse"));
		}




		/*
		Client client = ClientBuilder.newClient();
		String url = "http://localhost:8080/JerseyDemo/webapi/solutions";

		  WebTarget webTarget = client.target(url);
		    MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
		    formData.add(levelName, encodeSolution);
		    Response response = webTarget.request().post(Entity.form(formData));
		/*
		String url = "http://localhost:8080/JerseyDemo/webapi/solutions";


		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		SokobanSolution soko=new SokobanSolution(levelName, encodeSolution);


		MyJAXBBean bean =
		target.request(MediaType.APPLICATION_JSON_TYPE)
		    .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
		        MyJAXBBean.class);
		/*
		String url = "http://localhost:8080/JerseyDemo/webapi/solutions/addSolution";

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);



		WebResource webResource = client
		   .resource("http://localhost:8080/RESTfulExample/rest/json/metallica/post");

		String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";

		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class, input);




		/*
		String url = "http://localhost:8080/JerseyDemo/webapi/solutions/addSolution";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		SokobanSolution sokoSol = new SokobanSolution(levelName, encodeSolution);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.json(sokoSol));

		if (response.getStatus() == 204) {
			System.out.println("Solution added successfully");
		} else {
			System.out.println(response.getHeaderString("errorResponse"));
		}
		*/

	}
	private String getSolutionFromService(String name) {
		String url = "http://localhost:8080/SokobanService5/webapi/solutions?name=" + name;
		System.out.println("the name of the level is" + name);

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		Response response = webTarget.request(MediaType.TEXT_PLAIN).get(Response.class);
		if (response.getStatus() == 200) {
			String solution = response.readEntity(new GenericType<String>() {
			});
			System.out.println("the solution is:");
			System.out.println(solution);
			this.solution = solution;
			try {
				this.commands.put("Solution");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return solution;

		} else {
			System.out.println(response.getHeaderString("errorResponse"));
		}
		return null;

	}

	public BlockingQueue<String> getCommands() {
		return commands;
	}

	public boolean isStop() {
		return stop;
	}

	public void setCommands(BlockingQueue<String> commands) {
		this.commands = commands;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	private void stop() {
		this.stop = true;
	}

}

package com.datetime.datetime;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class main extends JavaPlugin implements Listener {
	
	private Scoreboard board;
	private Objective obj;
	private Score score;
	public ScoreboardManager sm = Bukkit.getScoreboardManager();
	
	public static HashMap<UUID, Integer> web = new HashMap<UUID, Integer>(); 
	
	public Player target;
	
	public String TotalDate;
	public String TotalTime;
	
	public int wart;
	//hex tir % 2
	
	public String year;
	public String month;
	public String day;
	
	public int count;
	
	//hex tir % 3
	
	public String hour;
	public String minute;
	public String second;
	
	//hex tir % 4
	
	public boolean ampm;
	
	
	//hex tir % 5(dtox a-6)
	
	public void itsme(UUID targetpl) {
		Player target = (Player) Bukkit.getPlayer(targetpl);
		
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		obj = board.registerNewObjective("mujudatetime", "dummy");
		
		obj.setDisplayName(ChatColor.YELLOW + ""  + ChatColor.BOLD + "< �� �� �� �� >"); //sidebar �� ����
		score = obj.getScore("���� �ð� :"); //Score ��� ��¥ �÷��̾ ����� �ݽô�
		score.setScore(6); //set 10
		
		
		score = obj.getScore("" + TotalDate); //Score ��� ��¥ �÷��̾ ����� �ݽô�
		score.setScore(5); //set 10
		
		score = obj.getScore( "" + TotalTime); //Score ��� ��¥ �÷��̾ ����� �ݽô�
		score.setScore(4); //set 10
		

		score = obj.getScore(""); //Score ��� ��¥ �÷��̾ ����� �ݽô�
		score.setScore(3); //set 10
		
		score = obj.getScore(ChatColor.AQUA + "");
		score.setScore(2); //set 10
		score = obj.getScore("");
		
		score = obj.getScore("[V1.041]"); //Score ��� ��¥ �÷��̾ ����� �ݽô�
		score.setScore(1); //set 10
		obj.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "dtz"); //sidebar �� ����
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		target.setScoreboard(board); //display
	}
	
	public void getdatetime() {
		ampm = false;
		String thisTail;
	    LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH");
	    DateTimeFormatter myFormatObj1 = DateTimeFormatter.ofPattern("mm");
	    DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("ss");
	    DateTimeFormatter myFormatObj3 = DateTimeFormatter.ofPattern("yyyy");
	    DateTimeFormatter myFormatObj4 = DateTimeFormatter.ofPattern("MM");
	    DateTimeFormatter myFormatObj5 = DateTimeFormatter.ofPattern("dd");
	    String formattedDate = myDateObj.format(myFormatObj);
	    String formattedDate1 = myDateObj.format(myFormatObj1);
	    String formattedDate2 = myDateObj.format(myFormatObj2);
	    String formattedDate3 = myDateObj.format(myFormatObj3); //�⵵
	    String formattedDate4 = myDateObj.format(myFormatObj4); //��
	    String formattedDate5 = myDateObj.format(myFormatObj5); //��
	   
	    int yay = Integer.parseInt(formattedDate);
	     
	    if(yay >= 13) {
	    	//13�� - 24���� �� 
	    	ampm = true;
	    	hour = Integer.toString(yay - 12);
	    	thisTail = "����";
	    }else {
	    	hour = Integer.toString(yay);
	    	thisTail = "����";
	    }
	    
	    
	    
	    TotalTime = thisTail + " " + hour + "�� " + formattedDate1 + "�� " + formattedDate2 + "��";
	    TotalDate = formattedDate3 + "�� " + formattedDate4 + "�� " + formattedDate5 + "��";
	    
	}
	
	@Override
	public void onEnable() {

		ConsoleCommandSender consol = Bukkit.getConsoleSender();

		consol.sendMessage(ChatColor.AQUA + "mujudatetime starts/");
		getServer().getPluginManager().registerEvents(this, this);
		
		
		wart = 0;
		
		int b = 0;
		if(Bukkit.getOnlinePlayers().size() > 0) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				count = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				    @Override
				    public void run() {
				        getdatetime();
				        groot(p.getUniqueId());
				        
				    }
				}, 0L, 20L);
				
				web.put(p.getUniqueId(), count);
				p.sendMessage(ChatColor.RED + "�ð� ä���� ����۵Ǿ����ϴ�. reloaded time server.");
			}
		}
	}
	
	public void groot(UUID its) {
		Player p = (Player) Bukkit.getPlayer(its);
		
		p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		
		itsme(p.getUniqueId());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("bb")) {
			Player p = (Player) Bukkit.getPlayer(sender.getName());
			
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			itsme(p.getUniqueId());
		}
		return true;
	}

	@EventHandler
	public void join(PlayerJoinEvent e) {
		
		count = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
		    @Override
		    public void run() {
		        getdatetime();
		        groot(e.getPlayer().getUniqueId());
		        
		    }
		}, 0L, 20L);
		
		web.put(e.getPlayer().getUniqueId(), count);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Bukkit.getScheduler().cancelTask(web.get(e.getPlayer().getUniqueId()));
	}
}


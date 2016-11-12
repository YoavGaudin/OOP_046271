package hw0q2;
import java.io.*;

/**
 * @author yoavg
 *
 */
public class CommentsParser {
	
	/**
	 * @requires args[0] = file name
	 * @effects if no IOException occurred, prints comments in file name
	 */
	public static void main(String[] args) throws IOException {
		String line_comments_re = ".*//.*";
		String blk_comments_re_open = ".*/\\*.*";
		String blk_comments_re_close = ".*\\*/.*";
		
		if (args.length != 1) {
			System.err.println(String.format(
					"CommentsParser class must recieve exactly 1 argument. " +
					"You passed %d arguments", args.length));
			return;
		}
		
		for (String arg: args) {
			System.out.println(arg);
		}
		
		try {
			BufferedReader file = new BufferedReader(new FileReader(args[0]));
			String line, line_to_print="";
			boolean in_comments_blk = false;
			while ((line = file.readLine()) != null) {
				if (line_to_print.length() > 0) {
					System.out.println(line_to_print);
					line_to_print = "";
				}
				
				if (line.matches(blk_comments_re_close) && in_comments_blk) {
					in_comments_blk = false;
					
					line_to_print = line_to_print.concat(line.replaceAll("\\*/.*", ""));
					System.out.println(String.format("DEBUGGER: close comments block. %s", line_to_print));
				}
				
				if (line.matches(blk_comments_re_open)) {
					in_comments_blk = true;
					
					line_to_print = line_to_print.concat(line.replaceAll(".*/\\*", ""));
					System.out.println(String.format("DEBUGGER: open comments block. %s", line_to_print));
					if (! line.matches(blk_comments_re_close)) {
						// if comments block is not closed on the same line then we're done
						continue;
					}
				}
				
				if (line.matches(line_comments_re)) {
					System.out.println("DEBUGGER: found comments line");
					line_to_print = line_to_print.concat(line.replaceAll(".*//", ""));
					continue;
				}
				
				if (in_comments_blk) {
					System.out.println(line);
				}
			}
			file.close();
		}
		catch (IOException e) {
			// TODO: handle exception
		}
	}
}

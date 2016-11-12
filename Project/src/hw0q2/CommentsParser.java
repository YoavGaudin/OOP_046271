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
			String line;
			boolean in_comments_blk = false;
			while ((line = file.readLine()) != null) {
				
				if (line.matches(line_comments_re)) {
					System.out.println(line.replaceFirst(".*?//", ""));
					continue;
				}
				
				if (line.matches(blk_comments_re_open) && line.matches(blk_comments_re_close)) {
					int open_block = line.indexOf("/*")+2;
					int close_block = line.indexOf("*/");
//					System.out.println(String.format("One line block comment- start: %d, end: %d", open_block, close_block));
					System.out.println(line.substring(open_block, close_block));
					continue;
				}
				
				if (line.matches(blk_comments_re_open)) {
					in_comments_blk = true;
					System.out.println(line.replaceFirst(".*?/\\*", ""));
					continue;
				}
				
				if (line.matches(blk_comments_re_close) && in_comments_blk) {
					in_comments_blk = false;
					System.out.println(line.replaceFirst("\\*/.*", ""));
					continue;
				}
				
				if (in_comments_blk) {
					System.out.println(line);
				}
			}
			file.close();
		}
		catch (IOException e) {
			System.err.println(String.format("Unable to open file \"%s\"", args[0]));
		}
	}
}

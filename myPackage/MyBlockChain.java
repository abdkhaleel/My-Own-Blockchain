package myPackage;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
public class MyBlockChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	
	public static void main(String[] args) {
		
		blockchain.add(new Block("Hi, I am the first Block", "0"));
		System.out.println("Trying to Mine Block 1..");
		blockchain.get(0).mineBlock(difficulty);
		
		
		blockchain.add(new Block("Hey, I am the second block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("\nTrying to Mine Block 2..");
		blockchain.get(1).mineBlock(difficulty);
		
		
		blockchain.add(new Block("Yo, I am the third block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("\nTrying to Mine Block 3..");
		blockchain.get(2).mineBlock(difficulty);
		
		
		System.out.println("\n\nBlockchain is Valid : " + isChainValid());
		
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe Blockchain: ");
		System.out.println(blockchainJson);
	}
	public static boolean isChainValid() {
		Block previousBlock;
		Block currentBlock;
		String target = new String(new char[difficulty]).replace('\0', '0');
		
		for(int i = 1; i < blockchain.size(); i++) {
			previousBlock = blockchain.get(i-1);
			currentBlock = blockchain.get(i);
			
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}
			
			if(!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			
			if(!currentBlock.hash.substring(0, difficulty).equals(target)) {
				System.out.println("This block has't been mined");
				return false;
			}
		}
		return true;
	}
}

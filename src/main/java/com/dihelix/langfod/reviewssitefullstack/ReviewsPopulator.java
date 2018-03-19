package com.dihelix.langfod.reviewssitefullstack;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewsPopulator implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(ReviewsPopulator.class);

	@Resource
	private ReviewRepository reviewRepository;
	@Resource
	private TagRepository tagRepository;
	@Resource
	private CategoryRepository categoryRepository;
	@Resource
	private CommentRepository commentRepository;


	@Override
	public void run(String... args) throws Exception {

		Map<String, Category> categories = populateCategoryMap();
		Map<String, Tag> tags = populateTagMap();
		List<Review> reviewList = new ArrayList<>();
			reviewList.add(reviewRepository.save(new Review("Bear", categories.get("meat"), "Content about Bears", LocalDate.now(),
					"This is a bear.",
					"http://www.placebear.com/200/250", gimmeTagSet(tags, "scratches", "furry"))));
		reviewList.add(reviewRepository.save(new Review("Steven Segal", categories.get("people"), "Content about Steven Segal", LocalDate.now(),
				"This is Steven Segal.",
				"http://www.stevensegallery.com/200/250", gimmeTagSet(tags, "actor"))));
		reviewList.add(reviewRepository.save(new Review("Nicolas Cage", categories.get("people"), "Content about Nicolas Cage", LocalDate.now(),
				"This is Nicolas Cage.",
				"http://www.placecage.com/c/200/250", gimmeTagSet(tags, "actor"))));
		reviewList.add(reviewRepository.save(new Review("Tour", categories.get("books"),
				"This was a comic book cover found on Flickr via the Creative Commons Search.\r\n The artist is Nathan Brown.\r\n"
						+ "No inforation about the actual comic book was found, so, have some Ipsum:\r\n\r\n"
						+ "E-markets, user-contributed deliver integrateAJAX-enabled revolutionize, generate, engineer, "
						+ "beta-test platforms generate, impactful experiences leverage."
						+ " Engage e-enable synergize sexy syndicate enable; generate; citizen-media benchmark optimize aggregate e-business create extend one-to-one;"
						+ "ecologies engineer enterprise viral, podcasts granular enterprise? Optimize seamless magnetic rich-clientAPIs open-source vertical collaborative? Enable; models extensible facilitate social Cluetrain; user-contributed; networks networkeffects, "
						+ "web services,"
						+ " partnerships implement strategic. 24/7 rss-capable, content web-readiness. Syndicate interfaces engineer: distributed e-services; plug-and-play portals design. Communities implement.\r\n"
						+ ".\r\n" + "\r\n"
						+ "Platforms extensible initiatives synergies leading-edge bricks-and-clicks end-to-end user-centric reintermediate scale leading-edge data-driven mesh. Morph visionary 24/365. Methodologies syndicate matrix synthesize user-centric networkeffects podcasting content B2C sexy deliver webservices unleash? Networkeffects expedite folksonomies compelling solutions; ecologies technologies brand back-end transform revolutionize, sticky, mindshare enable initiatives? Engage deliverables user-contributed robust innovative embrace synergies viral addelivery communities sexy? B2B engage turn-key transition compelling, real-time cutting-edge transition create embrace orchestrate strategic engage proactive users, whiteboard life-hacks innovative.",
				LocalDate.of(2010, Month.AUGUST, 1), "this is a comic book", "img/4924453496_b4d39c2cd8_m.jpg",
				gimmeTagSet(tags, "war", "fiction"))));
		// gimmeURL("https://farm5.staticflickr.com/4119/4924453496_b4d39c2cd8_m_d.jpg"),
		reviewList.add(reviewRepository.save(new Review("A Discovery of New Worlds", categories.get("books"), "March 21, 2017\r\n"
				+ "Format: Paperback\r\n"
				+ "A review of ‘A discovery of new worlds’ by Bernard de Fontenelle, translated by Aphra Behn.\r\n"
				+ "\r\n"
				+ "de Fontenelle, B. (Translated by Aphra Behn) (1688 and 2012, new edition with foreword by Paul Murdin). A discovery of new worlds. London: Hesperus Press Limited.\r\n"
				+ "\r\n" + "Reviewer: Dr W. P. Palmer\r\n" + "\r\n"
				+ "This is a strange book to modern scientific eyes, but interesting nonetheless. It was written more than 300 years ago, prior to Newton’s work on gravitation. It is scientific in the sense that Bernard de Fontenelle based his science on Descartes’ Vortex theory which was accepted at the time. This is well described in more detail by Paul Murdin in his contemporary introduction to the work. The story is of a conversation between a young teacher (the book’s author, Bernard de Fontenelle) and a titled lady (The Marquise). Fontenelle provides astronomical explanations of the universe over five nights trying to convince The Marquise that the moon, the planets and the stars are inhabited. The Marquise tends to question what she is being told by Fontenelle and then eventually agree with him. In modern terms, many of the discussion points made are ridiculous, but some sound impressive. The conclusion is that life is widely distributed in the Universe, which is still an accepted viewpoint. There are also prefaces by the English translator, Aphra Behn, who was one of the first English female professional writers and by the book’s author, Bernard de Fontenelle.\r\n"
				+ "\r\n" + "It is an unusual book, but could be of interest to many.\r\n" + "\r\n" + "BILL PALMER,",
				LocalDate.of(2012, Month.JANUARY, 1),
				"Translated by Aprah Behn, 1688. Hesperus Press 2012. 17th century popular astronomy",
				"img/8083124491_e49d85055b_m.jpg",
				gimmeTagSet(tags, "translated", "astronomy", "nonfiction"))));
		// gimmeURL("https://farm9.staticflickr.com/8330/8083124491_e49d85055b_m_d.jpg"),
		reviewList.add(reviewRepository.save(new Review(
				"Fifty Specialty Libraries of New York City: From Botany to Magic ", categories.get("books"),
				"Terry Ballard on the Making of “Fifty Specialty Libraries of New York City: From Botany to Magic”\r\n"
						+ "By Lisa Peet on February 16, 2016\r\n" + "\r\n"
						+ "Ballard headshotTerry Ballard, an academic systems librarian for more than 40 years, recently finished up an ambitious project: visiting, photographing, and writing about 50 of New York City’s special libraries. The resulting book, Fifty Specialty Libraries of New York City: From Botany to Magic, from Chandos Books, publishes today. LJ caught up with him shortly before the book’s launch in order to find out a little more about the process behind his odyssey—and what it was like to visit 50 libraries in less than a year.\r\n"
						+ "\r\n" + "\r\n"
						+ "Fifty Specialty LibrariesWhat was it like, visiting 50 libraries in less than a year?\r\n"
						+ "\r\n"
						+ "The whole idea was [intended] to be an adventure because I was going to personally visit 50 different libraries and talk to the head librarian, or somebody in authority. Also, in every case but two I used public transportation, the exceptions being New York Botanical Garden in the Bronx and the Staten Island Museum.\r\n"
						+ "\r\n"
						+ "It was very tiring, as it turned out. When I dreamed this up [I thought], oh, 50 libraries, that’s a manageable number. But when you have to make appointments and figure out transportation and so on, it was very grueling. In fact, the week that I was supposed to do my most intense visiting I got completely sick and could barely visit three or four libraries. So I was running perpetually late. It was a very short timeframe—I got the contract in November [2014], right around Thanksgiving, and they wanted the book turned in by June 1 [2015]. I didn’t quite make it. The last draft was sent July 15.\r\n"
						+ "\r\n"
						+ "There were a few libraries I wanted to visit that I just never got to. I figure if this book catches on and people like it, there will be a second edition with 60 libraries.",
				LocalDate.of(2016, Month.FEBRUARY, 16),
				"Everyone knows about the New York Public Library and the two lions that guard it. Most New Yorkers,..., do not know that the city contains many dozens of libraries outside of that system... There is a library devoted to magic, a dog library run by the American Kennel Club, a German language library, a library devoted to the works of Carl Jung, and a few subscription libraries that are centuries old and still going. Most of them are in Manhattan, but there are wonderful surprises in the outer boroughs, and we looked at those too.",
				"img/20096881228_03ef098231_m.jpg", gimmeTagSet(tags, "nonfiction", "new york city"))));
		// gimmeURL("https://farm1.staticflickr.com/310/20096881228_03ef098231_m_d.jpg"),
		reviewList.add(reviewRepository.save(new Review("Bacon",categories.get("meat"),
				"Spicy jalapeno bacon ipsum dolor amet irure leberkas magna kielbasa commodo boudin drumstick shoulder ex spare ribs shank pariatur minim cupidatat. Est fugiat dolore ipsum do in laboris sint venison eu drumstick andouille rump ad tenderloin. Ground round buffalo ipsum biltong meatloaf spare ribs meatball ut sint shankle filet mignon lorem doner ham hock. Tenderloin ball tip shankle cow meatloaf elit, consectetur eu jowl doner ipsum aliquip turducken laborum. Reprehenderit shankle nulla eiusmod dolore andouille quis swine.\r\n"
						+ "\r\n"
						+ "Flank culpa eu landjaeger in, ad esse ball tip frankfurter exercitation strip steak. Sausage voluptate tempor pancetta laboris laborum tail minim. Frankfurter andouille officia shankle. Fugiat proident shankle veniam, sirloin ut beef ribs spare ribs venison. Magna ball tip irure, in doner officia filet mignon t-bone ullamco. Fatback drumstick reprehenderit meatball chicken.\r\n"
						+ "\r\n"
						+ "Frankfurter sunt labore elit occaecat, id turducken. Nisi occaecat sunt aute ut brisket anim ea irure venison. Chicken cow sausage hamburger, exercitation magna esse capicola. Dolore ut exercitation leberkas turkey andouille.\r\n"
						+ "\r\n"
						+ "Aliquip boudin exercitation aute officia. Salami cupidatat jerky aute, pig ball tip in nisi pancetta consequat turkey. Buffalo hamburger meatball tenderloin, non beef spare ribs. Eiusmod drumstick beef ribs, turducken swine ham culpa aliquip cow pork chop. Tail pariatur drumstick, sausage eu ullamco short ribs pork veniam cupidatat aliqua sunt. Ipsum burgdoggen id veniam, sed proident boudin commodo beef ribs strip steak do laboris buffalo esse.",
				LocalDate.now(), "Using Bacon Ipsum to test", "https://baconmockup.com/200/250/",
				gimmeTagSet(tags, "bacon", "tasty"))));
		reviewList.add(reviewRepository.save(new Review("Small Furry Kittens",categories.get("meat"), "Birman egyptian mau\r\n"
				+ "Bombay jaguar thai havana brown\r\n"
				+ "Cat ipsum dolor sit amet, siberian singapura. Leopard. British shorthair singapura ocicat ragdoll, yet tabby. Birman mouser, ocelot and manx. Balinese american bobtail for manx so russian blue, singapura. Thai american shorthair yet ragdoll mouser mouser yet bobcat and cheetah. Havana brown jaguar. Ocicat. Leopard sphynx or siberian manx or bengal. American shorthair himalayan yet siberian, yet egyptian mau yet himalayan bengal yet devonshire rex. Cheetah thai but maine coon kitten ocicat yet tomcat, so tomcat. Savannah abyssinian , malkin but kitty. Tiger leopard so malkin. Jaguar siberian, grimalkin. Kitty thai puma yet scottish fold. Cheetah burmese kitty. Lynx tiger for bengal for thai. Maine coon. Malkin. Kitten bombay, yet munchkin so ocelot ocelot yet thai. Lynx bobcat yet cornish rex. Lion cougar yet kitten, persian. Jaguar russian blue for devonshire rex and kitten. Ocicat manx singapura and burmese or american shorthair. Thai birman.\r\n"
				+ "\r\n"
				+ "Bombay bombay leopard but jaguar havana brown tiger, for leopard. Donskoy cheetah. Cheetah american shorthair tom thai. Ocicat. American bobtail turkish angora so persian. Russian blue leopard persian manx. Maine coon ocelot siberian or panther yet ragdoll for malkin. Tabby british shorthair so ocelot kitty for burmese. Turkish angora. Turkish angora norwegian forest, manx egyptian mau munchkin. Cougar malkin and scottish fold so sphynx or persian panther or leopard. Jaguar kitten. Savannah persian and manx yet maine coon but lynx but ocicat for norwegian forest. Devonshire rex bobcat. Savannah himalayan leopard. Puma donskoy ragdoll tomcat. British shorthair kitten.\r\n"
				+ "\r\n"
				+ "American bobtail ocelot and siamese, tabby and munchkin. Puma. Turkish angora donskoy so thai for leopard. Himalayan tomcat ocelot for bombay. Singapura lynx egyptian mau puma or siamese. Lion siamese, persian and malkin. Persian cheetah, and maine coon for birman thai singapura. Manx. Malkin lynx for malkin savannah. ",
				LocalDate.now(), "Kitten Ipsum for testing", "http://placekitten.com/200/250",
				gimmeTagSet(tags, "cute", "furry", "scratches"))));
		Review m = reviewRepository.save(new Review("Bill Murray", categories.get("people"),
				"From Wikipedia, the free encyclopedia\r\n" + "\r\n" + "\r\n"
						+ "William James Murray (born September 21, 1950) is an American actor, comedian, and writer. He first gained exposure on Saturday Night Live, a series of performances that earned him his first Emmy Award, and later starred in comedy films—including Meatballs (1979), Caddyshack (1980), Stripes (1981), Tootsie (1982), Ghostbusters (1984), Scrooged (1988), Ghostbusters II (1989), What About Bob? (1991), and Groundhog Day (1993). He also co-directed Quick Change (1990).\r\n"
						+ "\r\n"
						+ "Murray garnered additional critical acclaim later in his career, starring in Lost in Translation (2003), which earned him a Golden Globe and a BAFTA Award for Best Actor, as well as an Academy Award nomination for Best Actor. He also received Golden Globe nominations for his roles in Ghostbusters, Rushmore (1998), Hyde Park on Hudson (2012), St. Vincent (2014), and the HBO miniseries Olive Kitteridge (2014), for which he later won his second Primetime Emmy Award.\r\n"
						+ "\r\n"
						+ "Murray received the Mark Twain Prize for American Humor in 2016. His comedy is known for its deadpan delivery. \r\n"
						+ "Murray is a partner with his brothers in Murray Bros. Caddy Shack, a restaurant located near St. Augustine, Florida. He resides in Charleston, South Carolina, where he is a very active community member.\r\n"
						+ "\r\n"
						+ "In 1978, Murray appeared in two at-bats for the Grays Harbor Loggers Minor League Baseball team, credited with one hit and a lifetime batting average of .500.\r\n"
						+ "\r\n"
						+ "He is a part-owner of the St. Paul Saints independent baseball team and occasionally travels to Saint Paul, Minnesota to watch the team's games. He also owns part of the Charleston RiverDogs, Hudson Valley Renegades, and the Brockton Rox. He has invested in a number of other minor league teams in the past, including the Utica Blue Sox, Fort Myers Miracle, Salt Lake Sting (APSL), Catskill Cougars and Salt Lake City Trappers. In 2012 he was inducted into the South Atlantic League Hall of Fame for his ownership and investment activities in the league.\r\n"
						+ "\r\n"
						+ "Being very detached from the Hollywood scene, Murray does not have an agent or manager and reportedly only fields offers for scripts and roles using a personal telephone number with a voice mailbox that he checks infrequently. This practice has the downside of sometimes preventing him from taking parts in films such as Who Framed Roger Rabbit, Monsters, Inc., The Squid and the Whale, Charlie and the Chocolate Factory, and Little Miss Sunshine. When asked about this practice, however, Murray seemed content with his inaccessibility, stating, \"It's not that hard. If you have a good script that's what gets you involved. People say they can't find me. Well, if you can write a good script, that's a lot harder than finding someone. I don't worry about it; it's not my problem.\"\r\n"
						+ "\r\n"
						+ "Murray's popularity has been such that (as of 2017) he holds an iconic status in U.S. popular culture. Murray's eccentric and irreverent style of comedy, both on-screen and in his personal life, has caused him to be seen as a folk hero to many making him a significant meme in various media including books and the internet. In 2016 he was awarded the Mark Twain Prize for American Humor by the Kennedy Center.",

				LocalDate.now(),
				"This is Bill Murray. Bill Murray is an actor and all around funny dude. He is known for movies like CaddyShack and Ghostbusters,",
				"http://www.fillmurray.com/200/250", gimmeTagSet(tags, "actor", "groundhog-catcher")));
			
		reviewList.add(m);
		Comment c = new Comment("tITLE", "amazing comment", "John Doe", LocalDateTime.now(),m);

		c = commentRepository.save(c);
				
		
		log.error("Comment: "+c.getCommentTitle());
		log.error("Review: "+ reviewRepository.findById(m.getId()).get().getComments());
		
		
	}
	private Map<String, Tag> populateTagMap() {
		Map<String, Tag> tags = new TreeMap<>();
		tags.put("actor", tagRepository.save(new Tag("actor")));
		tags.put("groundhog-catcher", tagRepository.save(new Tag("groundhog-catcher")));
		tags.put("cute", tagRepository.save(new Tag("cute")));
		tags.put("furry", tagRepository.save(new Tag("furry")));
		//tags.put("meat", tagRepository.save(new Tag("meat")));
		tags.put("scratches", tagRepository.save(new Tag("scratches")));
		tags.put("bacon", tagRepository.save(new Tag("bacon")));
		tags.put("tasty", tagRepository.save(new Tag("tasty")));
		tags.put("translated", tagRepository.save(new Tag("translated")));
		tags.put("astronomy", tagRepository.save(new Tag("astronomy")));
		//tags.put("french", tagRepository.save(new Tag("french")));
		//tags.put("book", tagRepository.save(new Tag("book")));
		tags.put("nonfiction", tagRepository.save(new Tag("nonfiction")));
		tags.put("war", tagRepository.save(new Tag("war")));
		//tags.put("graphic novels", tagRepository.save(new Tag("graphic novels")));
		tags.put("fiction", tagRepository.save(new Tag("fiction")));
		tags.put("new york city", tagRepository.save(new Tag("new york city")));
		return tags;
	}
	
	private Map<String, Category> populateCategoryMap() {
		Map<String, Category> categories = new TreeMap<>();
		categories.put("books", categoryRepository.save(new Category("books")));
		categories.put("meat", categoryRepository.save(new Category("meat")));
		categories.put("people", categoryRepository.save(new Category("people")));
		return categories;
	}
	
	private static Set<Tag> gimmeTagSet(Map<String, Tag> tags, String... names) {
		Set<Tag> outSet = new HashSet<>();
		for (String name : names) {
			outSet.add(tags.get(name));
		}
		return outSet;
	}
}

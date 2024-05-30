CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS articles (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  author_id INT,
  FOREIGN KEY (author_id) REFERENCES users(id)
);

INSERT INTO articles (title, content) 
VALUES ('The next James Bond', 'The search for the next James Bond has been a topic of intense speculation since Daniel Craig stepped down from the role after his final appearance in "No Time to Die" in 2021. Several actors have emerged as potential candidates, with Aaron Taylor-Johnson currently being the frontrunner.

### Aaron Taylor-Johnson
- **Current Status**: Multiple reports suggest that Aaron Taylor-Johnson has been formally offered the role of James Bond. He has been a favorite among bookmakers and has reportedly impressed producer Barbara Broccoli during a top-secret screen test in September 2022[1][2][3][4][5].
- **Background**: Known for his roles in "Kick-Ass," "Bullet Train," and "Nowhere Boy," Taylor-Johnson has a diverse acting portfolio that includes both action and dramatic roles[5].

### Other Contenders
- **Tom Hardy**: Frequently mentioned in discussions about the next Bond, Hardy''s rugged persona and acting chops make him a strong candidate[1][3].
- **Regé-Jean Page**: Gained fame from "Bridgerton" and has been a popular choice among fans and bookmakers[2][5].
- **Henry Cavill**: Known for his role as Superman, Cavill has expressed interest in playing Bond and is considered a strong contender[5].
- **James Norton**: Another favorite with bookmakers, Norton has a strong background in both film and television, including roles in "McMafia" and "Happy Valley"[3][5].
- **Damson Idris**: Best known for his role in "Snowfall," Idris is another name that has been floated as a potential Bond[1][5].

### Notable Mentions
- **Idris Elba**: Long considered a top choice, Elba has repeatedly taken himself out of the running, citing age as a factor[1][3][5].
- **Tom Hiddleston**: Known for his role in "The Night Manager," Hiddleston has the charisma and acting skills but may be too busy with other commitments[3].
- **Jonathan Bailey**: Known for "Bridgerton," Bailey has built a considerable body of work and is seen as a potential candidate[3].

### Conclusion
While Aaron Taylor-Johnson appears to be the leading candidate, the official announcement has yet to be made. The producers, Barbara Broccoli and Michael G. Wilson, are looking for someone who can play Bond for the next decade and fit the "thirty-something" profile, which narrows down the list of potential actors[3]. The final decision remains highly anticipated by fans and the film industry alike.

Citations:
[1] https://www.gq-magazine.co.uk/article/new-james-bond
[2] https://www.vogue.com/slideshow/who-will-be-the-next-james-bond
[3] https://www.gamesradar.com/next-james-bond/
[4] https://www.backstage.com/magazine/article/james-bond-casting-news-77117/
[5] https://www.bbc.com/news/uk-england-london-68601151');

INSERT INTO articles (title, content) 
VALUES ('Bestselling books released in 2024', 'Here are some of the bestselling books released in 2024, based on various sources:

### Amazon Best Sellers of 2024
1. **The Women: A Novel** by Kristin Hannah
2. **Atomic Habits: An Easy & Proven Way to Build Good Habits & Break Bad Ones** by James Clear
3. **Onyx Storm (Deluxe Limited Edition) (The Empyrean, 3)** by Rebecca Yarros
4. **Dog Man: The Scarlet Shedder: A Graphic Novel (Dog Man #12)** by Dav Pilkey
5. **Fourth Wing (The Empyrean, 1)** by Rebecca Yarros
6. **A Court of Thorns and Roses (A Court of Thorns and Roses, 1)** by Sarah J. Maas
7. **Iron Flame (The Empyrean, 2)** by Rebecca Yarros
8. **The Housemaid** by Freida McFadden
9. **The 48 Laws of Power** by Robert Greene
10. **Oh, the Places You''ll Go!** by Dr. Seuss[1].

### New York Times Best Sellers - April 21, 2024
1. **Just for the Summer** by Abby Jimenez
2. **The Women** by Kristin Hannah
3. **Table for Two** by Amor Towles
4. **Fourth Wing** by Rebecca Yarros
5. **Iron Flame** by Rebecca Yarros[4].

### WHSmith Best Books of 2024
1. **Homecoming: A Sweeping, Intergenerational Epic** by Kate Morton
2. **Past Lying: The twisty new Karen Pirie thriller** by Val McDermid
3. **The Last Dance: A Detective Miller case** by Mark Billingham
4. **House of Flame and Shadow** by Sarah J. Maas
5. **Taste of Blood: The thrilling new Jane Tennison crime novel** by Lynda La Plante[3].

### Curious Cat Bookshop Best New Release Books May 2024
1. **Finding Things** by Kevin Henkes and Laura Dronzek
2. **When Among Crows** by June Hur
3. **Weaving His Own Moving Family Story** by Lawrence Ingrassia
4. **Hornbeam and His Friends** by Stephen King
5. **Harkening to Agatha Christie’s Classic** by Ruth Ware[5].

These lists highlight a variety of genres and authors, showcasing the diversity of popular literature in 2024.

Citations:
[1] https://www.amazon.com/gp/bestsellers/2024/books
[2] https://www.horizonbooks.com/new-york-bestsellers-hardcover-fiction
[3] https://www.whsmith.co.uk/book-lists/best-books-of-the-year/
[4] https://www.nytimes.com/books/best-sellers/2024/04/21/
[5] https://curiouscatbookshop.com/list/best-new-release-books-may-2024');

INSERT INTO articles (title, content) 
VALUES ('Rumours about the new iPhone', 'Rumors about the upcoming iPhone 16 suggest a range of exciting new features and improvements over previous models. Here are some of the key points:

### Design and Display
- **Larger Screens**: The iPhone 16 Pro models are rumored to have larger screens, with sizes of 6.3 inches for the iPhone 16 Pro and 6.9 inches for the iPhone 16 Pro Max, compared to the current sizes of 6.1 and 6.7 inches[3].
- **Brighter Display**: The iPhone 16 Pro and Pro Max are expected to feature displays with a peak brightness of 1,200 nits, a 20% increase over the iPhone 15 Pro[1][2].
- **Slimmer Bezels**: There are rumors of slimmer bezels, which could result in slightly larger screen areas for some models[3].

### Battery and Performance
- **Longest Battery Life**: The iPhone 16 Pro Max is predicted to have the longest battery life of any iPhone ever made, thanks to an increase in energy density while maintaining the current battery size[1].
- **New Chipset**: All iPhone 16 models are expected to feature the next-generation Apple chipset, branded as the A18, with the Pro models getting more powerful versions[2][3].

### Camera and AI Features
- **Advanced Cameras**: The iPhone 16 Pro models may include 5x telephoto lenses and support for spatial video, enhancing zoom capabilities and video recording[2][3].
- **AI Enhancements**: The new iPhones are expected to feature generative AI in several Apple apps, including Photos and Notes, making Siri smarter and improving the Messages app''s ability to field questions and auto-complete sentences[1][2][3].

### New Buttons and Design Changes
- **Action and Capture Buttons**: The iPhone 16 is rumored to include an improved action button and a new capture button for recording video, which may even support spatial videos[1][5].
- **Solid-State Buttons**: There are mixed rumors about the iPhone 16 Pro having solid-state buttons with haptic feedback, similar to the home button on older iPhone models[5].

### Release Date and Pricing
- **Release Date**: The iPhone 16 is expected to be unveiled in the first or second week of September 2024, with pre-orders starting the same week and general availability a week later[1][3].
- **Pricing**: While specific prices are not confirmed, the base model is predicted to start from $799[2].

These rumors paint a picture of a significant upgrade over the iPhone 15, with enhancements in display, battery life, camera capabilities, and AI features. However, as with all rumors, these details should be taken with caution until official announcements are made.

Citations:
[1] https://www.independent.co.uk/extras/indybest/gadgets-tech/phones-accessories/iphone-16-rumours-release-date-apple-b2544906.html
[2] https://www.tomsguide.com/news/iphone-16
[3] https://www.cnet.com/tech/mobile/iphone-16-what-could-be-in-apple-next-iphone/
[4] https://www.cnet.com/tech/mobile/iphone-se-4-rumors-what-may-be-next-for-apples-budget-phone/
[5] https://www.youtube.com/watch?v=_c0gxdmggBU');

INSERT INTO articles (title, content) 
VALUES ('Independence Day', 'Independence Day is a significant holiday in the United States, celebrated annually on July 4. It commemorates the adoption of the Declaration of Independence on July 4, 1776, when the Second Continental Congress declared the Thirteen Colonies'' independence from Great Britain, establishing the United States of America[1][2].

The holiday is marked by various patriotic displays, including fireworks, parades, barbecues, family reunions, and other public and private events celebrating American history, government, and traditions. It is a federal holiday, with non-essential federal institutions closed on July 4, and federal employees taking the day off if July 4 falls on a weekend[1][2].

The holiday has a long history, dating back to the early years of the American Revolution. Initially, it was modeled after the celebration of the king''s birthday, but as the revolution progressed, it evolved into a celebration of independence and the birth of the new nation. Over time, it became a major midsummer holiday, with a focus on leisure activities such as picnics, baseball games, and fireworks displays[2].

In recent years, the holiday has also been associated with the 1996 science fiction film "Independence Day," which tells the story of a global alien invasion and the human resistance against it. The film was a commercial success and spawned a franchise with various video games, toys, and other merchandise[3][4].

Citations:
[1] https://en.wikipedia.org/wiki/Independence_Day_%28United_States%29
[2] https://www.britannica.com/topic/Independence-Day-United-States-holiday
[3] https://en.wikipedia.org/wiki/Independence_Day_%281996_film%29
[4] https://www.rottentomatoes.com/m/1071806-independence_day
[5] https://www.youtube.com/watch?v=B1E7h3SeMDk');

INSERT INTO articles (title, content) 
VALUES ('Places to visit in Romania', 'Romania is a country rich in history, culture, and natural beauty, offering numerous places to visit for tourists. Here are some of the most popular and must-see destinations:

1. **Bran Castle**: Known as Dracula''s Castle, this medieval fortress is a must-visit for its Gothic architecture and association with the famous vampire legend. It is located near Bran, Romania[1][9][11].

2. **Peleș Castle**: A stunning example of Neo-Renaissance architecture, Peleș Castle is a former royal residence and a UNESCO World Heritage Site. It is situated in Sinaia, Romania[2][9][11].

3. **Corvin Castle**: This impressive Gothic castle is a significant historical landmark in Hunedoara, Romania. It is known for its imposing structure and beautiful architecture[3][9][11].

4. **Salina Turda**: A popular tourist destination, Salina Turda is a salt mine with an underground lake and a unique atmosphere. It is located in Turda, Romania[4][9].

5. **Palace of Parliament**: The second-largest administrative building in the world, the Palace of Parliament is a significant architectural landmark in Bucharest, Romania. It is known for its grandeur and historical significance[5][10].

6. **The Romanian Athenaeum**: This beautiful concert hall is a symbol of Romanian culture and architecture. It is located in Bucharest, Romania[5][10].

7. **The Large Square**: A historic and vibrant square in Sibiu, Romania, it is known for its beautiful architecture and lively atmosphere[7][10].

8. **Brașov**: A charming city in Transylvania, Brașov is famous for its medieval architecture, scenic views, and cultural heritage[8][9][11].

9. **Transfagarasan Highway**: A scenic road that winds through the Carpathian Mountains, it is known for its stunning views and adventurous drive[9][11].

10. **Danube Delta**: A UNESCO World Heritage Site and a natural wonder, the Danube Delta is a popular destination for nature lovers and wildlife enthusiasts[9][11].

11. **Maramures**: A region known for its traditional rural life, wooden churches, and painted monasteries, Maramures is a great place to experience authentic Romanian culture[9][11].

12. **Bucovina**: A region famous for its monasteries, painted churches, and traditional architecture, Bucovina is a must-visit for those interested in history and culture[9][11].

These are just a few of the many places to visit in Romania. The country offers a diverse range of attractions, from historical landmarks to natural wonders, making it a fascinating destination for tourists.

Citations:
[1] https://castelulbran.ro
[2] https://peles.ro
[3] http://www.castelulcorvinilor.ro
[4] https://www.salinaturda.eu
[5] https://cic.cdep.ro
[6] http://fge.org.ro
[7] http://patrimoniu.sibiu.ro/en
[8] http://www.brasovcity.ro
[9] https://traveltastefeel.com/24-epic-places-to-visit-in-romania-with-map-many-bonus-ideas/
[10] https://www.mywanderlust.pl/places-to-visit-in-romania/
[11] https://www.romanianfriend.com/blog/best-things-to-do-in-romania
[12] https://theculturetrip.com/europe/romania/articles/the-12-most-beautiful-spots-in-romania');

INSERT INTO articles (title, content) 
VALUES ('Health benefits of dark chocolate', 'Dark chocolate, particularly those with high cocoa content, offers several health benefits due to its rich nutrient profile and bioactive compounds. Here are the key health benefits:

### 1. Rich in Minerals
Dark chocolate is a good source of essential minerals such as magnesium, iron, zinc, copper, and manganese. These minerals are crucial for various bodily functions, including maintaining healthy bones, supporting the immune system, and aiding in oxygen transport[1][2][3][5].

### 2. High in Antioxidants
Dark chocolate contains powerful antioxidants like flavanols, polyphenols, and catechins. These compounds help neutralize free radicals, reducing oxidative stress and potentially lowering the risk of chronic diseases[1][2][5].

### 3. Cardiovascular Health
The flavanols in dark chocolate have been shown to improve vascular health by enhancing blood flow, lowering blood pressure, and reducing the risk of cardiovascular diseases. They help in maintaining the flexibility of blood vessels and preventing arteriosclerosis[1][2][4][5].

### 4. Brain Function
Consuming dark chocolate may improve brain function by increasing blood flow to the brain. Flavanols have neuroprotective benefits, which can help maintain cognitive function and potentially reduce the risk of neurodegenerative diseases[1][2][4].

### 5. Gut Health
Dark chocolate can positively affect gut health by promoting the growth of beneficial gut bacteria. The polyphenols in dark chocolate help modulate the gut microbiota, which can trigger anti-inflammatory pathways and improve overall gut health[1][2].

### 6. Anti-Inflammatory Properties
The anti-inflammatory properties of dark chocolate can help reduce inflammation in the body, which is beneficial for preventing and managing conditions like heart disease and diabetes[2][4].

### 7. Potential Mood Improvement
Some studies suggest that dark chocolate can improve mood, possibly due to its effect on the gut-brain axis and the increased diversity of the gut microbiome[2].

### Nutritional Considerations
While dark chocolate is beneficial, it is also high in calories, fat, and sugar. Therefore, it should be consumed in moderation to avoid potential negative effects such as weight gain and increased risk of diabetes[1][2][4][5].

In summary, incorporating moderate amounts of high-cocoa dark chocolate into a balanced diet can provide several health benefits, particularly due to its rich mineral content and antioxidant properties.

Citations:
[1] https://www.bbcgoodfood.com/howto/guide/dark-chocolate-good-you
[2] https://www.medicalnewstoday.com/articles/dark-chocolate
[3] https://www.health.com/dark-chocolate-8361916
[4] https://www.webmd.com/diet/health-benefits-dark-chocolate
[5] https://www.healthline.com/nutrition/7-health-benefits-dark-chocolate');

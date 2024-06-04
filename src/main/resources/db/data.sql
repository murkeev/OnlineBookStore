INSERT INTO category (name) VALUES ('Дитяча література');
INSERT INTO category (name) VALUES ('Хоббі і дозвілля');
INSERT INTO category (name) VALUES ('Художня література');
INSERT INTO category (name) VALUES ('Психологія і взаємини');
INSERT INTO category (name) VALUES ('Саморозвиток');
INSERT INTO category (name) VALUES ('Бізнес');
INSERT INTO category (name) VALUES ('Гроші');
INSERT INTO category (name) VALUES ('Економіка');
INSERT INTO category (name) VALUES ('Біографії і мемуари');
INSERT INTO category (name) VALUES ('Вивчення мов');
INSERT INTO category (name) VALUES ('Фантастика');
INSERT INTO category (name) VALUES ('Фентезі');
INSERT INTO category (name) VALUES ('Книги іноземною мовою');


-- Вставка данных в таблицу authors
INSERT INTO author (name) VALUES ('Алекс Т. Сміт');
INSERT INTO author (name) VALUES ('Махо Мольфіно');
INSERT INTO author (name) VALUES ('Стівен Сілбігер');
INSERT INTO author (name) VALUES ('Дарка Озерна');
INSERT INTO author (name) VALUES ('Тімоті Снайдер');
INSERT INTO author (name) VALUES ('Луїза Хей');
INSERT INTO author (name) VALUES ('Станіслав Домагальскі');
INSERT INTO author (name) VALUES ('Андрій Курпатов');
INSERT INTO author (name) VALUES ('Массімо Пільюччі');
INSERT INTO author (name) VALUES ('Грегорі Лопес');
INSERT INTO author (name) VALUES ('Джим Коллінз');
INSERT INTO author (name) VALUES ('Джонні Томсон');
INSERT INTO author (name) VALUES ('Стівен Кові');
INSERT INTO author (name) VALUES ('Грегорі Клайс');
INSERT INTO author (name) VALUES ('Софія Фабьяновська-Міцик');
INSERT INTO author (name) VALUES ('Мішель Обама');
INSERT INTO author (name) VALUES ('Девід Аллен');
INSERT INTO author (name) VALUES ('Роальд Дал');
INSERT INTO author (name) VALUES ('Леопольд Тирманд');
INSERT INTO author (name) VALUES ('Максим Ільяхов');
INSERT INTO author (name) VALUES ('Людмила Саричева');
INSERT INTO author (name) VALUES ('Найл Кіштайні');
INSERT INTO author (name) VALUES ('Тереса Папріс');
INSERT INTO author (name) VALUES ('Яніна Яслан');


-- Вставка данных в таблицу images
--INSERT INTO image (url) VALUES ('http://example.com/image1.jpg');
--INSERT INTO image (url) VALUES ('http://example.com/image2.jpg');
--INSERT INTO image (url) VALUES ('http://example.com/image3.jpg');

-- Вставка данных в таблицу books
INSERT INTO book (title, description, year, price, total_quantity, is_expected, discount, language)
VALUES ('Містер Пінґвін. Книга 1. Втрачений скарб', 'Містер Пінґвін — авантюрист і пінгвін у стильному капелюсі — завжди сповнений бажання знайти пригоду. І тому коли директорка Музею незвичайних предметів Будіка Бонз кличе його на допомогу, містер Пінґвін починає діяти. Чи зможе він разом зі своїм партнером і другом павуком Коліном знайти зниклий скарб? І що чекає на героїв, коли пригоди стануть небезпечними?', 2021, 240, 100, FALSE, 10, 'укр'),
       ('Покінчи з "хорошою дівчинкою". Як переписати застарілі правила, відкрити в собі джерело сили і творити наповнене життя', 'Протягом тисячоліть жінок вчили бути "хорошими", а не сильними. Але "хороші" дівчата повинні стримувати свій голос та прагнення, що шкодить, в першу чергу, їм самим. Авторка книжки, коуч, психологиня, ведуча подкасту the Heroine Махо Мольфіно завдяки досвіду, отриманому під час численних консультацій та розмов з жінками-лідерками, виділила п’ять саморуйнівних тенденцій. Це п’ять міфів про "хорошу дівчинку": про правила, досконалість, логіку, гармонію та жертовність, які кожна жінка повинна подолати, щоб мати владу над своїм життям.', 2020, 300, 200, TRUE, 15, 'укр'),
       ('MBA за 10 днів', 'У своїй книжці про 10-денний курс MBA, яка є міжнародним бестселером, автор фахово й з гумором розповідає про те, як, навчаючись самос­тійно, скласти та втілити маркетинговий план, розібратися у фінансовій документації, побудувати успішну комунікацію з колегами й керівництвом, розвивати корпоративну стратегію і вільно розмовляти на мові MBA. Тут знайдете інструменти успішного ведення бізнесу, яких навчають у найкращих бізнес-школах світу.', 2019, 300, 150, FALSE, 5, 'укр'),
       ('Ви це зможете! 7 складових здорового способу життя', 'Це посібник зі свідомого ставлення до життя і покращення здоров’я. У ньому містяться рекомендації щодо раціону, внормування маси тіла і сну, фізичної активності та медичних обстежень. Всі тези та висновки базуються на сучасних наукових даних, медичних рекомендаціях та протоколах діагностики і лікування. Авторка легко і детально пояснює, як зважено і свідомо ставитися до власного здоров’я і способу життя, не впадати у крайнощі, не вірити в чарівні таблетки, магічне мислення і не вдаватися до суворих обмежень. А окрім цього, посібник написаний для і про сучасних українців і українок, з глибоким розумінням та емпатією до їхніх щоденних викликів. Книжка проілюстрована художницею Ольгою Дегтяровою.', 2020, 220, 150, FALSE, 5, 'укр'),
       ('Нариси таємної війни. Польський художник на службі визволення Радянської України', 'У центрі «Нарисів таємної війни» Тімоті Снайдера — запаморочлива шпигунська історія польського художника і розвідника Ґенрика Юзевського — одного з найближчих соратників Юзефа Пілсудського, віце-міністра внутрішніх справ українського уряду в 1920 році, волинського воєводи у міжвоєнний період, багаторічного підпільника і конспіратора. Його драматичну біографію Снайдер майстерно поєднує з глибоким історичним дослідженням проукраїнських політичних та інтелектуальних еліт Польщі першої половини ХХ століття — прометеїстів, їхньої участі у протистоянні більшовицькому терору та, пізніше, сталінізму, в амбітному проєкті визволення Радянської України. Це захоплива й динамічна розповідь про змагання польської та радянської розвідок, і водночас ґрунтовна наукова праця на базі сотень архівних джерел.', 2023, 250, 150, FALSE, 5, 'укр'),
       ('Цілюща сила думки', 'Усі ми бажаємо одного - щастя. Щодня шукаємо його в обличчях випадкових перехожих, приємних дрібничках, малих і великих звершеннях. Нам здається, що щастя недосяжне, воно ходить далеко від нас. Ця книжка розкриє найзаповітнішу таємницю сховку щастя. Адже воно - у кожному з нас. Авторка пояснює, як відкинути всі негативні упередження й думки, що заважають бути щасливими та насолоджуватися життям. Вона радить звернутися до внутрішньої мудрості, полюбити та прийняти себе таким, яким ми є. Луїза Хей вчить долати бар''єри болю, страху та гніву і бути вдячним за всі дари Всесвіту. Ключ до щастя - у наших руках.', 2018, 150, 150, FALSE, 0, 'укр'),
       ('Великий польсько-український, українсько-польський словник. Термінологія сучасного бізнесу. 200 000 слів', 'Даний сучасний великий польсько-український, українсько-польський словник включає 200 000 лексичних одиниць.', 2010, 300, 150, FALSE, 5, 'укр-пол'),
       ('Червона таблетка. Поглянь правді у вічі. Книга для інтелектуальної меншості', '«Червона таблетка. Поглянь правді у вічі» — результат багаторічних досліджень відомого психолога Андрія Курпатова, написаний у доступному науково-популярному стилі. У книзі зібрані найважливіші наукові відкриття в галузі нейрофізіології, які допоможуть читачеві розібратися в принципах роботи мозку й ефективніше використовувати його для вирішення важливих завдань. Ви навчитеся розуміти поведінку й мотиви інших людей і взаємодіяти з ними для досягнення цілей. Питання про сенс життя перестане мучити вас, бо ви зрозумієте, чого хочете насправді і яким чином зможете цього досягти.', 2021, 300, 150, FALSE, 5, 'укр'),
       ('Нові стоїки. 52 тижні для наповненого життя', 'Книжка про філософію стоїцизму — вчення, яке сьогодні набуває все більше популярності. Античні стоїки уміли те, що багатьом із нас сьогодні не під силу — жити у гармонії із собою, підтримувати баланс в житті між матеріальним та духовним, не турбуватися через те, що неможливо змінити. Та, врешті, всі вони були по-справжньому щасливими і насолоджувалися життям. У сучасному світі люди весь час кудись поспішають, живуть у постійному стресі та під тиском дедлайнів. Ця книжка — справжній практичний посібник зі змін свого життя на краще, розрахований на цілий рік (52 тижні). Вона не просто пояснює принципи стоїцизму, а ще й дає конкретні вправи, які допоможуть звільнитися від тягаря психологічної напруги, усвідомити внутрішню силу та почати насолоджуватися моментом. Це книжка, створена не для читання, а для дій.', 2021, 350, 150, FALSE, 0, 'укр'),
       ('Книга Від хорошого до величного', 'У цій книжці Джим Коллінз аналізує діяльність і структуру компаній, яким вдалося здійснити прорив у вищу лігу бізнесу. Він відібрав фірми, які, попри всі кризи, п’ятнадцять років поспіль показували найкращі результати у своїй галузі. Як Gillette, Kimberly-Clark, Philip Morris та іншим вдалося взяти настільки високу планку? Джим Коллінз вивів формулу — дев’ять кроків, які дозволять розкрутити маховик успіху до швидкості, яка приносить шалені прибутки.', 2022, 400, 150, FALSE, 5, 'укр'),
       ('Filozofia dla zabieganych', 'Коли качка не качка? Чому ми любимо дивитися фільми жахів? Чи варто робити ставку на існування Бога? Що робить задоволення кращим за біль? Філософія для зайнятих людей – це захоплююча подорож світом ідей найбільших мислителів від античності до наших днів. Дізнайтеся, що вони говорять про найважливіші питання життя та чому варто знати їхні концепції сьогодні. Пориньте в міні-розділи, проілюстровані піктограмами, які розширять ваш кругозір, змусять задуматися, надихнуть і розважать. Їхній тематичний діапазон захоплює дух: від стратегій Сунь Цзи для перемоги в настільних іграх до думок Фрейда про наш потяг до смерті; від поглядів де Бовуар, який вважав материнський інстинкт міфом, до інопланетян Фермі та принципів робототехніки Азімова! Чи, можливо, ви хочете знати, чому Шопенгауер був би не найкращою компанією для вечірок? Усе це та багато іншого ви знайдете в цій унікальній книзі.', 2021, 700, 150, FALSE, 0, 'пол'),
       ('7 nawyków skutecznego działania', '7 звичок надзвичайно ефективних людей Стівена Р. Кові - біблія сучасної людини. Один з найвпливовіших американських людей вчить, як повірити у власні сили та збільшити вплив своїх дій, керуючись при цьому внутрішньою цілісністю та повагою до інших людей. Виявляється, розвиток семи звичок призводить до творчих внутрішніх змін, формування довготривалих стосунків з оточуючими та прийняття надихаючого лідерства на особистому, міжособистісному та управлінському рівні. Усі свої тези автор книги підкріплює прикладами з власного досвіду.', 2020, 500, 150, FALSE, 0, 'пол'),
       ('Utopia: The History of an Idea', 'Прагнення до кращого - навіть досконалого - суспільства існувало впродовж усієї історії, часто уявлялося у вигадливих деталях філософами, поетами, соціальними реформаторами, архітекторами та художниками. Ця книга досліджує вічно актуальну ідею: пошук ідеального суспільства. Ґреґорі Клейс досліджує вплив ідеї утопії на історію. Центральне місце в його дослідженні ідеальних світів займають міфи про створення світу; архетипи раю та потойбічного життя; нові світи та подорожі відкриттів; епохи революцій та технічного прогресу; зразкові громади та кібуци; політичні та екологічні антиутопії; космічні подорожі та наукова фантастика. Висвітлено найважливіші утопії в історії - як передбачувані, так і спроби їх реалізації, включаючи бачення ідеального суспільства на Заході, а також в Америці, Азії, Африці та арабському світі. Від класичних часів до сьогодення ця захоплююча книга простежує непереборну потребу людини уявляти і конструювати ідеальні світи.', 2021, 600, 150, FALSE, 5, 'анг'),
       ('Banzai. Japonia dla dociekliwych', 'Другий том серії "Світ для допитливих": дитячий путівник відомими та невідомими особливостями японської культури та ландшафту. Чому Японію називають країною квітучої сакури? Що означає червона крапка на японському прапорі? Чи завжди "чмокнути" означає "поцілувати"? Звідки з''явився котик Хеллоу Кітті? Чому в Японії злодії роззуваються, коли грабують будинки?', 2022, 350, 150, FALSE, 5, 'пол'),
       ('Becoming', 'За своє життя, сповнене сенсу і досягнень, Мішель Обама стала однією з найбільш знакових і переконливих жінок нашої епохи. Як перша леді Сполучених Штатів Америки - перша афроамериканка на цій посаді - вона допомогла створити найбільш гостинний та інклюзивний Білий дім в історії, а також зарекомендувала себе як потужний захисник жінок і дівчат у США та в усьому світі, кардинально змінивши способи ведення сім''ями здорового та активного способу життя, і стояла поруч зі своїм чоловіком, коли він вів Америку через деякі з її найважчих моментів. Попутно вона показала нам кілька танцювальних рухів, розтрощила Carpool Karaoke і виростила двох приземлених доньок під невблаганним поглядом ЗМІ.', 2018, 1000, 150, FALSE, 0, 'пол'),
       ('Getting Things Done: The Art of Stress-Free Productivity ', 'З моменту своєї першої публікації майже п''ятнадцять років тому книга Девіда Аллена "Getting Things Done" стала однією з найвпливовіших бізнес-книг своєї епохи, а також найкращою книгою про особисту організацію. "GTD" - це скорочення для цілого способу підходу до професійних та особистих завдань, яке породило цілу культуру веб-сайтів, організаційних інструментів, семінарів та відгалужень. Аллен переписав книгу від початку до кінця, доповнивши свій класичний текст важливими аспектами нового робочого місця та додавши матеріал, який зробить книгу свіжою та актуальною на довгі роки. Це нове видання "Getting Things Done" вітатимуть не лише сотні тисяч її прихильників, а й ціле нове покоління, яке прагне перейняти її перевірені принципи.', 2015, 400, 150, FALSE, 5, 'анг'),
       ('Matylda', 'Першу книгу Роальд написав у 1966 році для своїх дітей і не збирався її публікувати, хоча на той час вже писав оповідання, повісті і п''єси. Родичі вмовили його віднести казкову повість до редакції, і мали рацію. Книга мала шалений успіх. Після цього з''явилися й інші творіння, не менш талановиті та успішні, 1988 року «Матільду» визнали найкращою книгою для дітей. Прекрасна історія про талановиту, не за роками розвинену, самотню і незрозумілу ні батьками, ні вчителями дівчинці, яка вирішила перевиховати дорослих.', 2015, 400, 150, FALSE, 0, 'пол'),
       ('Zły', 'Книга - легенда. Найпопулярніший детектив, написаний у комуністичну епоху. Насправді, це надзвичайно інтелігентний опис народної Польщі, одягнений у форму детективу, роману чи вестерну. Все починається з таємничих нападів. Нападники - хулігани. Народна міліція стоїть на головах, щоб дістати єдиного праведника - безликого шерифа, завдяки якому люди відчувають себе в безпеці. Підпільний світ Варшави йде на війну. У героя є лише одна візитна картка - надзвичайний, світлий погляд. А на задньому плані - любовна історія, віссю якої є Марта Маєвська, мимоволі втягнута в кримінальне життя Варшави.', 2022, 650, 150, FALSE, 0, 'пол'),
       ('«Пиши, скорочуй. Як створити дієвий текст»', 'Максим Ільяхов і Людмила Саричева на конкретних прикладах демонструють, що добре, а що ні в інформаційних, рекламних, журналістських і публіцистичних текстах. Як писати листи, на які відповідатимуть, і розсилки, від яких не відписуватимуться. Як створювати дієві й коректні рекламні оголошення. Як викладати думки стисло, чітко та переконливо, без мовного сміття, фальші й штампів. Дотримуючись рекомендацій, викладених у книзі, навчитеся писати зрозуміло та захопливо, так, аби вам повірили. ', 2019, 250, 150, FALSE, 0, 'укр'),
       ('Krótka historia ekonomii', 'Якщо ви хочете дізнатися, чи справді гроші правлять світом, погляньте на цю блискуче написану книгу. Звідки береться бідність? Чи справді економічні кризи неминучі? Державне втручання в економіку - це прокляття чи необхідність? У цій захопливій і дотепній книжці автор, історик економіки, відповідає на ці фундаментальні питання та надає доступний огляд основних економічних ідей, а також сил і дилем, що формують сучасний світ. Окремі розділи присвячені поворотним моментам у світовій економічній історії, зокрема винайденню грошей, зародженню капіталізму та Великій депресії. Читач дізнається про феномени підприємництва та нерівності, разом з автором аналізує поведінкову економіку та фінансові кризи. Не обійшлося в книзі і без біографій таких мислителів, як Адам Сміт, Карл Маркс та Джон Мейнард Кейнс.', 2023, 450, 150, FALSE, 0, 'пол'),
       ('Słownik hiszpańsko-polski polsko-hiszpański', 'Практичний, зручний іспансько-польський та польсько-іспанський словник. Укладений з використанням комп''ютерного аналізу найпоширеніших слів і значень в іспанській мові. Словник містить - понад 100 000 значень - складова вимова для поляків - практичний словник ідіом - основна наукова термінологія - найнеобхідніші фрази - глосарій іспанських імен.', 2006, 150, 150, FALSE, 5, 'пол-ісп'),
       ('Słownik terminologii prawniczej i ekonomicznej Angielsko-Polski', '70 000 термінів з права, економіки, економіки, банківської справи, комерції, страхування, оподаткування, поштових послуг, митниці та транспорту. Покажчики - міри і ваги, абревіатури, організації, юридичні документи. Для працівників судових та адміністративних органів, зовнішньоекономічної діяльності, дипломатичної служби, а також журналістів, перекладачів, студентів. ', 1995, 400, 150, FALSE, 5, 'пол-анг');


-- Вставка данных в таблицу book_categories
INSERT INTO book_categories (book_id, category_id) VALUES (1, (SELECT id FROM category WHERE name='Дитяча література'));
INSERT INTO book_categories (book_id, category_id) VALUES (1, (SELECT id FROM category WHERE name='Хоббі і дозвілля'));
INSERT INTO book_categories (book_id, category_id) VALUES (1, (SELECT id FROM category WHERE name='Художня література'));
INSERT INTO book_categories (book_id, category_id) VALUES (2, (SELECT id FROM category WHERE name='Психологія і взаємини'));
INSERT INTO book_categories (book_id, category_id) VALUES (2, (SELECT id FROM category WHERE name='Саморозвиток'));
INSERT INTO book_categories (book_id, category_id) VALUES (3, (SELECT id FROM category WHERE name='Саморозвиток'));
INSERT INTO book_categories (book_id, category_id) VALUES (3, (SELECT id FROM category WHERE name='Бізнес'));
INSERT INTO book_categories (book_id, category_id) VALUES (3, (SELECT id FROM category WHERE name='Гроші'));
INSERT INTO book_categories (book_id, category_id) VALUES (3, (SELECT id FROM category WHERE name='Економіка'));
INSERT INTO book_categories (book_id, category_id) VALUES (4, (SELECT id FROM category WHERE name='Психологія і взаємини'));
INSERT INTO book_categories (book_id, category_id) VALUES (4, (SELECT id FROM category WHERE name='Саморозвиток'));
INSERT INTO book_categories (book_id, category_id) VALUES (4, (SELECT id FROM category WHERE name='Хоббі і дозвілля'));
INSERT INTO book_categories (book_id, category_id) VALUES (5, (SELECT id FROM category WHERE name='Біографії і мемуари'));
INSERT INTO book_categories (book_id, category_id) VALUES (6, (SELECT id FROM category WHERE name='Психологія і взаємини'));
INSERT INTO book_categories (book_id, category_id) VALUES (6, (SELECT id FROM category WHERE name='Саморозвиток'));
INSERT INTO book_categories (book_id, category_id) VALUES (7, (SELECT id FROM category WHERE name='Вивчення мов'));
INSERT INTO book_categories (book_id, category_id) VALUES (8, (SELECT id FROM category WHERE name='Саморозвиток'));
INSERT INTO book_categories (book_id, category_id) VALUES (8, (SELECT id FROM category WHERE name='Психологія і взаємини'));
INSERT INTO book_categories (book_id, category_id) VALUES (9, (SELECT id FROM category WHERE name='Саморозвиток'));
INSERT INTO book_categories (book_id, category_id) VALUES (10, (SELECT id FROM category WHERE name='Біографії і мемуари'));
INSERT INTO book_categories (book_id, category_id) VALUES (11, (SELECT id FROM category WHERE name='Книги іноземною мовою'));
INSERT INTO book_categories (book_id, category_id) VALUES (11, (SELECT id FROM category WHERE name='Саморозвиток'));
INSERT INTO book_categories (book_id, category_id) VALUES (11, (SELECT id FROM category WHERE name='Психологія і взаємини'));
INSERT INTO book_categories (book_id, category_id) VALUES (12, (SELECT id FROM category WHERE name='Книги іноземною мовою'));
INSERT INTO book_categories (book_id, category_id) VALUES (12, (SELECT id FROM category WHERE name='Саморозвиток'));
INSERT INTO book_categories (book_id, category_id) VALUES (12, (SELECT id FROM category WHERE name='Психологія і взаємини'));
INSERT INTO book_categories (book_id, category_id) VALUES (13, (SELECT id FROM category WHERE name='Книги іноземною мовою'));
INSERT INTO book_categories (book_id, category_id) VALUES (13, (SELECT id FROM category WHERE name='Хоббі і дозвілля'));
INSERT INTO book_categories (book_id, category_id) VALUES (13, (SELECT id FROM category WHERE name='Фантастика'));
INSERT INTO book_categories (book_id, category_id) VALUES (13, (SELECT id FROM category WHERE name='Фентезі'));
INSERT INTO book_categories (book_id, category_id) VALUES (14, (SELECT id FROM category WHERE name='Книги іноземною мовою'));
INSERT INTO book_categories (book_id, category_id) VALUES (14, (SELECT id FROM category WHERE name='Дитяча література'));
INSERT INTO book_categories (book_id, category_id) VALUES (14, (SELECT id FROM category WHERE name='Хоббі і дозвілля'));
INSERT INTO book_categories (book_id, category_id) VALUES (14, (SELECT id FROM category WHERE name='Художня література'));
INSERT INTO book_categories (book_id, category_id) VALUES (15, (SELECT id FROM category WHERE name='Біографії і мемуари'));
INSERT INTO book_categories (book_id, category_id) VALUES (15, (SELECT id FROM category WHERE name='Книги іноземною мовою'));
INSERT INTO book_categories (book_id, category_id) VALUES (16, (SELECT id FROM category WHERE name='Саморозвиток'));
INSERT INTO book_categories (book_id, category_id) VALUES (16, (SELECT id FROM category WHERE name='Книги іноземною мовою'));
INSERT INTO book_categories (book_id, category_id) VALUES (16, (SELECT id FROM category WHERE name='Бізнес'));
INSERT INTO book_categories (book_id, category_id) VALUES (16, (SELECT id FROM category WHERE name='Гроші'));
INSERT INTO book_categories (book_id, category_id) VALUES (16, (SELECT id FROM category WHERE name='Економіка'));
INSERT INTO book_categories (book_id, category_id) VALUES (17, (SELECT id FROM category WHERE name='Книги іноземною мовою'));
INSERT INTO book_categories (book_id, category_id) VALUES (17, (SELECT id FROM category WHERE name='Дитяча література'));
INSERT INTO book_categories (book_id, category_id) VALUES (17, (SELECT id FROM category WHERE name='Хоббі і дозвілля'));
INSERT INTO book_categories (book_id, category_id) VALUES (17, (SELECT id FROM category WHERE name='Художня література'));
INSERT INTO book_categories (book_id, category_id) VALUES (18, (SELECT id FROM category WHERE name='Книги іноземною мовою'));
INSERT INTO book_categories (book_id, category_id) VALUES (18, (SELECT id FROM category WHERE name='Хоббі і дозвілля'));
INSERT INTO book_categories (book_id, category_id) VALUES (18, (SELECT id FROM category WHERE name='Фантастика'));
INSERT INTO book_categories (book_id, category_id) VALUES (18, (SELECT id FROM category WHERE name='Фентезі'));
INSERT INTO book_categories (book_id, category_id) VALUES (19, (SELECT id FROM category WHERE name='Саморозвиток'));
INSERT INTO book_categories (book_id, category_id) VALUES (20, (SELECT id FROM category WHERE name='Бізнес'));
INSERT INTO book_categories (book_id, category_id) VALUES (20, (SELECT id FROM category WHERE name='Гроші'));
INSERT INTO book_categories (book_id, category_id) VALUES (20, (SELECT id FROM category WHERE name='Економіка'));
INSERT INTO book_categories (book_id, category_id) VALUES (20, (SELECT id FROM category WHERE name='Саморозвиток'));
INSERT INTO book_categories (book_id, category_id) VALUES (20, (SELECT id FROM category WHERE name='Книги іноземною мовою'));
INSERT INTO book_categories (book_id, category_id) VALUES (21, (SELECT id FROM category WHERE name='Вивчення мов'));
INSERT INTO book_categories (book_id, category_id) VALUES (21, (SELECT id FROM category WHERE name='Книги іноземною мовою'));
INSERT INTO book_categories (book_id, category_id) VALUES (22, (SELECT id FROM category WHERE name='Вивчення мов'));
INSERT INTO book_categories (book_id, category_id) VALUES (22, (SELECT id FROM category WHERE name='Книги іноземною мовою'));


-- Вставка данных в таблицу book_authors
INSERT INTO book_authors (book_id, author_id) VALUES (1, (SELECT id FROM author WHERE name='Алекс Т. Сміт'));
INSERT INTO book_authors (book_id, author_id) VALUES (2, (SELECT id FROM author WHERE name='Махо Мольфіно'));
INSERT INTO book_authors (book_id, author_id) VALUES (3, (SELECT id FROM author WHERE name='Стівен Сілбігер'));
INSERT INTO book_authors (book_id, author_id) VALUES (4, (SELECT id FROM author WHERE name='Дарка Озерна'));
INSERT INTO book_authors (book_id, author_id) VALUES (5, (SELECT id FROM author WHERE name='Тімоті Снайдер'));
INSERT INTO book_authors (book_id, author_id) VALUES (6, (SELECT id FROM author WHERE name='Луїза Хей'));
INSERT INTO book_authors (book_id, author_id) VALUES (7, (SELECT id FROM author WHERE name='Станіслав Домагальскі'));
INSERT INTO book_authors (book_id, author_id) VALUES (8, (SELECT id FROM author WHERE name='Андрій Курпатов'));
INSERT INTO book_authors (book_id, author_id) VALUES (9, (SELECT id FROM author WHERE name='Массімо Пільюччі'));
INSERT INTO book_authors (book_id, author_id) VALUES (9, (SELECT id FROM author WHERE name='Грегорі Лопес'));
INSERT INTO book_authors (book_id, author_id) VALUES (10, (SELECT id FROM author WHERE name='Джим Коллінз'));
INSERT INTO book_authors (book_id, author_id) VALUES (11, (SELECT id FROM author WHERE name='Джонні Томсон'));
INSERT INTO book_authors (book_id, author_id) VALUES (12, (SELECT id FROM author WHERE name='Стівен Кові'));
INSERT INTO book_authors (book_id, author_id) VALUES (13, (SELECT id FROM author WHERE name='Грегорі Клайс'));
INSERT INTO book_authors (book_id, author_id) VALUES (14, (SELECT id FROM author WHERE name='Софія Фабьяновська-Міцик'));
INSERT INTO book_authors (book_id, author_id) VALUES (15, (SELECT id FROM author WHERE name='Мішель Обама'));
INSERT INTO book_authors (book_id, author_id) VALUES (16, (SELECT id FROM author WHERE name='Девід Аллен'));
INSERT INTO book_authors (book_id, author_id) VALUES (17, (SELECT id FROM author WHERE name='Роальд Дал'));
INSERT INTO book_authors (book_id, author_id) VALUES (18, (SELECT id FROM author WHERE name='Леопольд Тирманд'));
INSERT INTO book_authors (book_id, author_id) VALUES (19, (SELECT id FROM author WHERE name='Максим Ільяхов'));
INSERT INTO book_authors (book_id, author_id) VALUES (19, (SELECT id FROM author WHERE name='Людмила Саричева'));
INSERT INTO book_authors (book_id, author_id) VALUES (20, (SELECT id FROM author WHERE name='Найл Кіштайні'));
INSERT INTO book_authors (book_id, author_id) VALUES (21, (SELECT id FROM author WHERE name='Тереса Папріс'));
INSERT INTO book_authors (book_id, author_id) VALUES (22, (SELECT id FROM author WHERE name='Яніна Яслан'));
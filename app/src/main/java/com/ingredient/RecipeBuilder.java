package com.ingredient;


import com.ingredient.objects.recipeModel.Ingredient;
import com.ingredient.objects.recipeModel.Recipe;
import com.ingredient.objects.recipeModel.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeBuilder {
    private List<Ingredient> ingredients;
    private List<Recipe> recipes;
    private List<Ingredient> targetIngredients;
    private List<Step> targetSteps;


    public RecipeBuilder() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe(1,"Котлеты по-киевски",
                "Котлеты по-киевски известны всем и, наверняка, многие имеют хотя бы теоретическое представление о сложности технологии их приготовления, благодаря которой, отчасти, это блюдо и овеяно некоторой тайной. Предлагаю немного приподнять таинственную завесу и взглянуть на процесс приготовления этого блюда не так стандартно. Итак, известно (это показывают во всех кулинарных передачах), что самая настоящая, классическая котлета по-киевски по всем строгим канонам готовится из цельной куриной грудки — она отделяется от неразделанной курицы вместе с крыльевой косточкой, которую в конце увенчает изящная бумажная папильотка. Затем филе хорошенько отбивается, в него укладывается кусочек сливочного масла и закрывается «малым» филе, отделенным от основного и все это заворачивается плотным рулетом, дважды панируется белыми сухарями и хорошенько обжаривается во фритюре. Все. Теперь самая главная задача для нашего изделия, чтобы масло не вытекло ведь именно по этому параметру будет оцениваться удалась или нет котлета по-киевски.",
                "http://tishka.org/master%20class/chicken%20Kiev/main.jpg",
                startIngredients()
                        .addIngredient(new Ingredient("куриные грудки","3шт.",1))
                        .addIngredient(new Ingredient("сливочное масло","60г.",2))
                        .addIngredient(new Ingredient("яйца","2шт.",3))
                        .addIngredient(new Ingredient("панировочные сухари","200г.",4))
                        .addIngredient(new Ingredient("мука","6ст.л.",5))
                        .addIngredient(new Ingredient("соль","",6))
                        .addIngredient(new Ingredient("черный перец","",7))
                        .addIngredient(new Ingredient("растительное масло","1л.",8))
                        .commitIngredient(),
                startSteps()
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/2.jpg","Каждую куриную грудку вымыть, зачистить от жира и убрать остатки костей и хрящей. Если у Вас есть весы взвесьте зачищенные филе. Обычно одно филе бройлера весит 250г."))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/3.jpg","Из каждого филе мы получим 2 котлеты. Необходимо, чтоб мясная заготовка для каждой котлеты весила 80г (можно принять и другой вес, например, 100г). Если грудка весит 250 г, мы готовим из нее 2 котлеты по 80г на сырую заготовку, нам надо срезать 90г филе (250-(80+80)=90). Филе с неровной рыхлой нижней части для котлет менее всего пригодно — им и пожертвуем. Необходимо прижать грудку ладонью сверху и срезать 1/3 часть снизу. Нижняя часть в приготовлении не понадобится (ее можно использовать для других блюд). Вес гладкой верхней части должен быть не менее 160г (80г+80г). "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/4.jpg","Формируем заготовки для 2-х котлет. Разрезаем грудку поперек на 2 равные части. Каждый кусок должен весить примерно 80г. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/5.jpg","Накрыть каждый кусок пленкой."))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/6.jpg","Отбить молоточком до толщины 5 мм."))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/7.jpg","Посолить (1 маленькая щепотка соли), слегка поперчить. Приправляется только одна сторона. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/8.jpg","Масло (60г) разрезать на 6 брусков (для 6-ти котлет)."))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/9.jpg","Теплом рук сформировать из брусков масла продолговатые овальные «котлетки». "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/10.jpg","Масляные заготовки кидаем в миску с холодной водой для закрепления формы."))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/11.jpg","Формируем котлеты по-киевски :Выложить мясную заготовку гладкой стороной наверх. Важный момент: рыхлая сторона лучше панируется чем гладкая, на рыхлую сторону сухари прилипнут лучше, поэтому гладкую сторону заворачиваем внутрь, рыхлую — наружу.На край выложить овальный сливочный брусочек. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/12.jpg","Подворачивая края так чтобы они закрыли масло начинаем заворачивать мясную отбивную рулетом. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/13.jpg","Масло должно быть полностью закрыто мясом — иначе может вытечь при жарке."))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/14.jpg","Должен получится рулетик."))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/15.jpg","Мокрыми руками сжимаем рулетик ладонями так чтобы из него вышел лишний воздух. Сжимать надо аккуратно, плотно, но без особых усилий. Эта процедура делается, чтобы при жарке котлеты не разворачивались, не всплывали во фритюре и равномерно прожаривались. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/16.jpg","Такая котлетка должна получится. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/17.jpg","Выкладываем котлеты на плоскую тарелку или дощечку и отправляем в холодильник на 30-40 минут, они должны хорошенько остыть. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/18.jpg","Через пол часа подготавливаем яичный льезон: яйца вылить в миску. Посолить (2 маленькие щепотки соли). "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/19.jpg","Перемешать вилкой."))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/20.jpg","Котлеты по-киевски панируют по схеме: мука-яичный льезон-сухари-яичный льезон-сухари. Для панировки котлет подготовить плоскую тарелку с мукой, мисочку с панировочными сухарями, тарелку с яичным льезоном, доску обсыпанную панировочными сухарями для выкладывания запанированных котлет. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/21.jpg","Сухими руками хорошенько обвалять каждую котлетку в муке."))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/22.jpg","Затем окунуть в яичный льезон. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/23.jpg","Обсушить руки. Обвалять котлеты в сухарях. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/24.jpg","Окунуть снова во взбитые яйца. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/25.jpg","Обсушить руки. Окончательно обвалять в сухарях."))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/26.jpg","Выложить на доску. Остудить котлеты, отправив в холодильник на 30 минут. Если планируете приготовить котлеты позже — в состоянии полуфабриката котлеты могут храниться в холодильнике до 12 часов. Вы можете все подготовить утром и поджарить котлеты к вечеру. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/27.jpg","В подходящую кастрюлю (она должна быть толстостенная, широкая, так чтобы не соприкасаясь могли одновременно жарится 3 котлеты) вылить 1 л рафинированного масла для жарки (без запаха). Слой масла должен быть таким, чтобы покрыть котлеты полностью, т.е. не меньше 8 см. Хорошенько нагреть масло до температуры 140 С. Проверить температуру можно кинув хлебную крошку — если она запузырилась масло хорошо нагрето. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/28.jpg","При помощи шумовки аккуратно выложить котлеты в хорошо разогретое масло. Работа с раскаленным фритюром опасна! В горячее масло не должна попасть вода иначе будет брызгать во все стороны. Аккуратно с рядом бродящими родственниками и котами, чтоб не было ожогов! "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/29.jpg","На среднем огне обжарить 6 минут (при условии, что котлеты небольшие, до 120г). Можно проверить готовность, надрезав кончик первой котлеты: если мясо сыровато увеличьте длительность обжарки на пару минут. Слишком долгой обжаркой можно пересушить котлету и мясо потеряет сочность и нежность. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/30.jpg","Аккуратно достать готовые котлеты шумовкой. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/31.jpg","выложить на блюдо. "))
                        .addStep(new Step("http://tishka.org/master%20class/chicken%20Kiev/32.jpg","Подавать на маленькой греночке-крутоне или с гарниром, например с картофельным пюре. Приятного аппетита! "))
                        .commitStep()));

        recipes.add(new Recipe(2,"Горячие бутерброды с яйцом и сыром",
                "Хороший старенький рецепт для любителей сыра. Раньше готовила такие горячие бутерброды довольно часто, потом подзабылись и теперь опять о них вспомнила. Делать их очень просто: тертый твердый сыр смешивается с сырыми яйцами, смесь укладывается на кусочки батона и запекается в хорошо разогретой духовке. Раньше я пекла бутерброды при 180 С около 25 минут, сейчас пеку при 250 С около 12 минут и так мне нравится больше: хлеб становится хрустящим, но не пересушивается, а сырное суфле получается более сочным с характерной румяной корочкой.\n" +
                        "Советы:\n" +
                        "— при выпечке сырная масса красиво поднимается шапочкой, но когда противень достанете она немного опадает — не пугайтесь так и должно быть.\n" +
                        "— хлеб не обязательно брать супер свежий — можно использовать вчерашний.\n" +
                        "— На одну порцию достаточно 3 шт. бутербродов.",
                "http://tishka.org/master%20class/Hot%20sandwiches%20with%20egg%20and%20cheese/gorjachie-buterbrody-s-jajcom-i-syrom.jpg",
                startIngredients()
                        .addIngredient(new Ingredient("батон","половина",9))
                        .addIngredient(new Ingredient("твердый сыр","180г.",10))
                        .addIngredient(new Ingredient("яйца","2шт.",3))
                        .commitIngredient(),
                startSteps()
                        .addStep(new Step("http://tishka.org/master%20class/Hot%20sandwiches%20with%20egg%20and%20cheese/2.jpg","Включить духовку нагреваться на 250 С. Хлеб нарезать ломтиками. Должно быть 6 кусочков толщиной 1 см. "))
                        .addStep(new Step("http://tishka.org/master%20class/Hot%20sandwiches%20with%20egg%20and%20cheese/3.jpg","Сыр натереть на крупной терке. "))
                        .addStep(new Step("http://tishka.org/master%20class/Hot%20sandwiches%20with%20egg%20and%20cheese/4.jpg","Сыр соединить с яйцами. "))
                        .addStep(new Step("http://tishka.org/master%20class/Hot%20sandwiches%20with%20egg%20and%20cheese/5.jpg","Хорошенько перемешать. Солить не нужно. "))
                        .addStep(new Step("http://tishka.org/master%20class/Hot%20sandwiches%20with%20egg%20and%20cheese/6.jpg","Кусочки батона выложить на противень. "))
                        .addStep(new Step("http://tishka.org/master%20class/Hot%20sandwiches%20with%20egg%20and%20cheese/7.jpg","Выложить сырную смесь на кусочки хлеба. "))
                        .addStep(new Step("http://tishka.org/master%20class/Hot%20sandwiches%20with%20egg%20and%20cheese/8.jpg","Выпекать при 250С в предварительно разогретой духовке до зарумянивания сырной корочки. Обычно это занимает 12-15 минут. Можно выпекать и при более низкой температуре более длительное время, но хлеб может пересушиться. "))
                        .addStep(new Step("http://tishka.org/master%20class/Hot%20sandwiches%20with%20egg%20and%20cheese/9.jpg","Подавать горячими. Приятного аппетита! "))
                        .commitStep()));


        recipes.add(new Recipe(3,"Греческий салат",
                "Греческий салат на своей родине называется просто «деревенский» и готовится тоже очень просто: крупно нарезанные овощи (огурцы, помидоры, пару колец лука и иногда сладкий перец) выкладываются порционно в тарелку, посыпаются горстью оливок и венчаются плоским куском неразрезанной феты, далее все это посыпается сухим орегано и трава поливается ароматным оливковым маслом, к которому теперь уже присоединяется и аромат душицы (по-гречески «ригани»). В чем волшебство cпросите Вы? А волшебство таится в использовании самых лучших южных помидоров, в сладком фиолетовом луке без слез (в наших просторах это сорт «ялтинский», в Европе — «сицилийский»), в замечательных греческих оливках «Каламата», в рассольной домашней фете, в прекрасном критском масле холодного отжима «extra virgin», в уютной атмосфере греческих таверн, где подается этот салат и, возможно, в маленькой рюмке белоснежного узо? ",
                "http://tishka.org/master%20class/greek%20salad/grecheskij-salat.jpg",
                startIngredients()
                        .addIngredient(new Ingredient("помидоры","2шт.",12))
                        .addIngredient(new Ingredient("свежие огурци","2шт.",13))
                        .addIngredient(new Ingredient("репчатый лук","половина",14))
                        .addIngredient(new Ingredient("болгарский перец","1шт.",15))
                        .addIngredient(new Ingredient("фета","200г.",16))
                        .addIngredient(new Ingredient("греческие оливки","20шт.",17))
                        .addIngredient(new Ingredient("оливковое масло","4ст.л.",18))
                        .addIngredient(new Ingredient("сухое орегано","4щепотки",19))
                        .commitIngredient(),
                startSteps()
                        .addStep(new Step("http://tishka.org/master%20class/greek%20salad/2.jpg","Лук нарезать тонкими полукольцами. "))
                        .addStep(new Step("http://tishka.org/master%20class/greek%20salad/3.jpg","Перец почистить от серединки и семечек и нарезать тонкими кольцами. "))
                        .addStep(new Step("http://tishka.org/master%20class/greek%20salad/4.jpg","Помидоры нарезать крупными дольками. "))
                        .addStep(new Step("http://tishka.org/master%20class/greek%20salad/5.jpg","У огурцов отрезать торцы, если кожица горчит нужно снять ее ножом, нарезать огурцы шайбами в 1-1,5 см толщиной. "))
                        .addStep(new Step("http://tishka.org/master%20class/greek%20salad/6.jpg","Фету нарезать пластинами поперек в 0,7-1 см толщиной. "))
                        .addStep(new Step("http://tishka.org/master%20class/greek%20salad/7.jpg","Овощи примерно разделите на количество порций (рецепт рассчитан на 4-ре порции). На тарелку выложить 1 порцию овощей(согласно продуктам по этому рецепту это будет: 1/2 помидора, 1/2 огурца, 1/4 половинки лука, 1/4 перца). "))
                        .addStep(new Step("http://tishka.org/master%20class/greek%20salad/8.jpg","Овощи перемешать или оставить горками/слоями в любой последовательности (я рекомендую перемешать). Выложить на овощи оливки и выложить фету сверху салата. "))
                        .addStep(new Step("http://tishka.org/master%20class/greek%20salad/9.jpg","Посыпать фету орегано. "))
                        .addStep(new Step("http://tishka.org/master%20class/greek%20salad/10.jpg","Полить оливковым маслом орегано, оно будет отдавать свой аромат маслу, а масло распределяться по салату. Подавать сразу же. Приятного аппетита! "))
                        .commitStep()));

 recipes.add(new Recipe(4,"Шашлык из свинины в духовке",
                "С шашлыками на костре, конечно, ничто не сравнится, но если выехать на природу не удается — предлагаю устроить пикник на балконе, а шашлык испечь в духовке!\n" +
                        "Некоторые советы:\n" +
                        "Основная проблема с домашними шашлыками из духовки (кроме той, что они не пахнут дымком) в том, что они часто получаются пересушенными. Чтобы этого избежать:\n" +
                        "— мясо лучше выбирать с тонкими прожилками жира типа свиного ошейка, жир, растапливаясь, сохраняет сочность и нежность мясных кусочков.\n" +
                        "— печь шашлык надо на хорошем жаре градусов 250 в предварительно разогретой духовке, поближе к спирали нагрева, т.о. от высокой температуры сразу образуется корочка, которая будет удерживать сок внутри.\n" +
                        "— имитируйте процесс жарки на углях, каждые 5-10 минут проворачивайте шампуры, поливайте маринадом или водичкой, следите чтобы мясо не подгорело.\n" +
                        "— Общее время приготовления составляет 20-25 мин (чтобы не пересушить).\n" +
                        "— Используем настоящие шампуры (не деревянные палочки, которые сразу сгорают).\n" +
                        "Если все сделать правильно, удачный домашний шашлык получается сочным, с чудесной румяной корочкой, повышает настроение повара и гостей и вправду немного пахнет дымком! ",
                "http://tishka.org/master%20class/shashlik/shashlyk-iz-svininy-v-duxovke.jpg",
                startIngredients()
                        .addIngredient(new Ingredient("свинина","800г.",20))
                        .addIngredient(new Ingredient("репчатый лук","4шт.",14))
                        .addIngredient(new Ingredient("соль","2ч.л.",6))
                        .addIngredient(new Ingredient("черный перец","0.5ч.л",7))
                        .addIngredient(new Ingredient("уксус","2ст.л",21))
                        .addIngredient(new Ingredient("вода","0.3л",22))
                        .commitIngredient(),
                startSteps()
                        .addStep(new Step("http://tishka.org/master%20class/shashlik/2.jpg","Мясо нарежьте кусками 4×5см. Лучше выбирать мясо с тонкими прожилками жира, например ошеек. При приготовлении жир будет плавится и смягчать мясные волокна — шашлык получится сочным. Если Вам не нравится,когда в шашлыке попадаются жирные куски, мясо на шампуре можно чередовать: кусочек постный, кусочек полужирного ошейка, за счет такого «соседства» даже постная вырезка получается сочной. "))
                        .addStep(new Step("http://tishka.org/master%20class/shashlik/3.jpg","Лук нарежьте шайбочками 2-3 мм толщиной. "))
                        .addStep(new Step("http://tishka.org/master%20class/shashlik/4.jpg","Я люблю самый простой маринад на уксусе, с солью и черным перцем. Мясо выложить в мисочку, посолить(2 ч.л. без верха, поперчить(0,5 ч.л. свежемолотого перца), залить водичкой (0,3л) и добавить 2 ст.л. уксуса. Перемешать. Уксус добавляют в маринад для того, чтобы мясо стало мягким, но слишком много уксуса лить нельзя иначе шашлык будет кислым на вкус. "))
                        .addStep(new Step("http://tishka.org/master%20class/shashlik/5.jpg","Переложить мясо луком. "))
                        .addStep(new Step("http://tishka.org/master%20class/shashlik/6.jpg","Ставим мясо под пресс. Мисочку накрыть пленкой, на мясо положить тарелку меньшего диаметра чем диаметр миски и сверху поставить груз — например 5-и литровую банку с водой.Маринуем мясо минимум час. (если печь собираетесь через час-два в холодильник мясо можно не ставить, если на следующий день — обязательно ставьте всю конструкцию в холодное место).P.S: Мясо после этой стадии прекрасно подходит и для выпекания на углях на мангале. "))
                        .addStep(new Step("http://tishka.org/master%20class/shashlik/7.jpg","Нанизываем мясо на шампуры вдоль волокон, чередуя шайбочками лука. Кусочки должны прилегать плотно. Длина шампура должна быть меньше глубины духовки и полностью в ней помещаться желательно не по диагонали, лучше сразу проверить влезают ли шампуры. "))
                        .addStep(new Step("http://tishka.org/master%20class/shashlik/8.jpg","Разогреваем духовку до 250 градусов, выкладываем шампуры на решетку под ней ставим пустой противень, жарим шашлык часто переворачивая и смачивая остатками маринада или простой водой. Следим чтобы мясо в духовке не подгорело. Общее время приготовления 20-25 мин. Слишком долго печь не стоит иначе шашлык может стать сухим."))
                        .addStep(new Step("http://tishka.org/master%20class/shashlik/9.jpg","Приятного аппетита! "))
                        .commitStep()));

 recipes.add(new Recipe(5,"Узвар из сухофруктов",
                "Компот из сухофруктов, который варят в Сочельник или к православным праздникам часто называют узвар, сравнивая технологию приготовления одного и другого понимаю, что у нас в семье готовят именно узвар, разница между этими напитками кажется не столь существенной, но важно помнить: при варке компота сухофрукты сортируют по типу и закладывают варится в разное время, в зависимости от длительности приготовления, первыми забрасывают груши, затем через время яблоки, потом идет изюм и т.д. Узвар варят иначе: сухие фрукты (любого типа) заливают водой, дают им хорошенько разбухнуть и после этого проваривают 5-10 минут разом все, поскольку сушки основательно напитываются водой приготавливаются они быстро как если бы варился обычный компот из свежих фруктов. И узвару и компоту после снятия с плиты надо отдохнуть, настоятся, чтобы стать еще ароматнее. Вместо сахара можно добавить мед. ",
                "http://tishka.org/master%20class/compote%20of%20dried%20fruits/uzvar-iz-suxofruktov.jpg",
                startIngredients()
                        .addIngredient(new Ingredient("сухофрукты","200г.",23))
                        .addIngredient(new Ingredient("вода","3-4л.",22))
                        .addIngredient(new Ingredient("сахар","6-8ст.л.",24))
                        .commitIngredient(),
                startSteps()
                        .addStep(new Step("http://tishka.org/master%20class/compote%20of%20dried%20fruits/2.jpg","Сухофрукты хорошенько промыть под проточной водой в дуршлаге. Залить 3-4 литрами воды (из расчета на 200 г сухофруктов). Дать настоятся сушкам в воде не меньше 4 часов. Удобно замачивать фрукты с вечера, а утром варить узвар. "))
                        .addStep(new Step("http://tishka.org/master%20class/compote%20of%20dried%20fruits/3.jpg","Воду с сухофруктами довести до кипения и кипятить на среднем огне 10 минут. От долгого настаивания фрукты хорошо набухают и не требуют долгого кипячения вне зависимости от того какие сушки Вы используете, обычно груши варят дольше, но при длительном замачивании это не необходимо."))
                        .addStep(new Step("http://tishka.org/master%20class/compote%20of%20dried%20fruits/4.jpg","Добавить сахар. На каждый литр воды я добавляю 3 полных с верхом столовых ложки сахара, получается довольно сладко. "))
                        .addStep(new Step("http://tishka.org/master%20class/compote%20of%20dried%20fruits/5.jpg","Довести еще раз до кипения, накрыть крышкой и снять с плиты. Узвар должен хорошо настояться и полностью остыть. Чем дольше стоит узвар тем ароматнее становится (в разумных приделах, конечно). Хорошо его сварить с утра и пить вечером, если готовите вечером — пить утром. "))
                        .addStep(new Step("http://tishka.org/master%20class/compote%20of%20dried%20fruits/6.jpg","Приятного аппетита! "))
                        .commitStep()));
 recipes.add(new Recipe(6,"Горячий шоколад",
                "Напиток на основе шоколада.",
                "http://kamelena.ru/recipes/image/200/248/f248-goryachij-shokolad.jpg",
                startIngredients()
                        .addIngredient(new Ingredient("шоколад","100г.",25))
                        .addIngredient(new Ingredient("сливки","200мл.",26))
                        .addIngredient(new Ingredient("молоко","300мл.",27))
                        .addIngredient(new Ingredient("крахмал","1ст.л.",28))
                        .commitIngredient(),
                startSteps()
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/248/01.jpg","Шоколад поломать на кусочки."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/248/02.jpg","Крахмал залить 50 мл молока, перемешать."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/248/03.jpg","Остальное молоко и сливки налить в кастрюлю с толстым дном, добавить шоколад."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/248/04.jpg","Нагревать, постоянно помешивая, пока весь шоколад хорошо не расплавится, и смесь станет однородной. Затем довести почти до кипения, влить разведенный крахмал, перемешать, выключить огонь."))
                        .commitStep()));
 recipes.add(new Recipe(7,"Фаршированные крабовые палочки",
                "Простое и легкое блюдо на закуску.",
                "http://kamelena.ru/recipes/image/200/226/f226-farshirovannye-krabovye-palochki.jpg",
                startIngredients()
                        .addIngredient(new Ingredient("крабовые палочки","200г.",29))
                        .addIngredient(new Ingredient("твердый сыр","100г.",10))
                        .addIngredient(new Ingredient("вареное яйцо","1шт",30))
                        .addIngredient(new Ingredient("чеснок","1 зубчик",31))
                        .addIngredient(new Ingredient("майонез",32))
                        .commitIngredient(),
                startSteps()
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/226/01.jpg","Сыр натереть на мелкой терке."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/226/02.jpg","Яйцо натереть на мелкой терке."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/226/03.jpg","В емкость положить сыр, яйцо и чеснок, выдавленный через чеснокодавилку. Заправить майонезом, хорошо перемешать в однородную массу."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/226/04.jpg","Чтобы крабовую палочку проще было развернуть, помять ее немного с боков."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/226/05.jpg","Затем палочку развернуть."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/226/06.jpg","Равномерно смазать сырной смесью."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/226/07.jpg","Завернуть обратно."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/200/226/08.jpg","Разрезать наискосок пополам."))
                        .commitStep()));
 recipes.add(new Recipe(8,"Клубничные шарики",
                "Простое и эффектное блюдо с клубникой на десерт.",
                "http://kamelena.ru/recipes/image/450/498/f498-shariki-s%20klubnikoj.jpg",
                startIngredients()
                        .addIngredient(new Ingredient("клубника","300г.",33))
                        .addIngredient(new Ingredient("творог","200г.",34))
                        .addIngredient(new Ingredient("песочное печенье","200г",35))
                        .addIngredient(new Ingredient("сливочное масло","100г.",2))
                        .addIngredient(new Ingredient("сахарная пудра","20г.",36))
                        .addIngredient(new Ingredient("кокосовая стружка","20г.",37))
                        .commitIngredient(),
                startSteps()
                        .addStep(new Step("http://kamelena.ru/recipes/image/450/498/01.jpg","Печенье тщательно измельчить."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/450/498/02.jpg","Мягкое сливочное масло взбить с сахарной пудрой, или же просто активно перемешать ложкой. Количество сахарной пудры регулируйте по вкусу, а также в зависимости от того, насколько сладкое у вас печенье."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/450/498/03.jpg","Добавить творог, хорошо перемешать. У меня творог жирностью 9%. Творог обязательно нужен однородный, пастообразный, не зернистый (если вы сомневаетесь такой ли у вас творог, протрите его через мелкий дуршлаг)."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/450/498/04.jpg","Частями всыпать печенье, хорошо перемешивая ложкой или лопаткой."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/450/498/05.jpg","Вам может понадобиться больше или меньше печенья. Должно получиться уже не липкое тесто, с которым будет удобно работать, но не слишком плотное и твердое."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/450/498/06.jpg","Отделить от массы кусочек теста и распластать в лепешку."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/450/498/07.jpg","Внутрь положить клубнику."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/450/498/08.jpg","Обернуть ягоду тестом и скатать в руках шарик. Если плохо лепится можно поставить тесто охладится в холодильник."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/450/498/09.jpg","Обвалять в кокосовой стружке."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/450/498/10.jpg","Или в натертом шоколаде, орехах и т.п. Поставить в холодильник на 1-2 часа для полного охлаждения."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/450/498/11.jpg","Во так выглядят внутри те, которые в «шоколаде» :)"))
                        .commitStep()));
 recipes.add(new Recipe(9,"Чизкейк с муссом из черной смородины",
                "Очень вкусный и сытный десерт с черной смородиной",
                "http://kamelena.ru/recipes/image/500/507/f507-cheesecake-s-mussom-i-jele-iz%20chernoj-smorodiny.jpg",
                startIngredients()
                        .addIngredient(new Ingredient("песочное печенье","200г",35))
                        .addIngredient(new Ingredient("сливочное масло","80г.",2))
                        .addIngredient(new Ingredient("кокосовая стружка","20г.",37))
                        .addIngredient(new Ingredient("какао","1ст.л.",38))
                        .addIngredient(new Ingredient("творог","500г.",34))
                        .addIngredient(new Ingredient("сливки","500мл.",26))
                        .addIngredient(new Ingredient("сахар","300г.",24))
                        .addIngredient(new Ingredient("крахмал","15г.",28))
                        .addIngredient(new Ingredient("кокосовая стружка","20г.",37))
                        .addIngredient(new Ingredient("яйца","3шт",3))
                        .addIngredient(new Ingredient("черная смородина","750г.",39))
                        .addIngredient(new Ingredient("желатин","15г.",40))
                        .commitIngredient(),
                startSteps()
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/01.jpg","Печенье, кокосовую стружку, какао положить в комбайн."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/02.jpg","Измельчить в мелкую крошку."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/03.jpg","Масло растопить (я растапливаю в микроволновке), немного остудить. Влить масло, еще раз измельчить все вместе."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/04.jpg","Высыпать полученные крошки в форму (моя форма 22 см), тщательно утрамбовать по дну. Поставить форму в разогретую до 160 градусов духовку, выпекать 10-15 минут. Затем полностью остудить."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/05.jpg","Подготовим начинку. Творог и сливки положить в емкость, хорошо растереть вместе погружным блендером в однородную гладкую массу. Если у вас творог не однородный, протрите его предварительно через сетчатый дуршлаг. В готовой массе не должны чувствоваться никакие крупинки творога! Если вы используете сливочный сыр, его достаточно просто размять лопаткой или венчиком, блендер не нужен. Продукты желательны комнатной температуры, так они легче объединятся."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/06.jpg","Добавить все остальные ингредиенты начинки, хорошо перемешать до полной однородности (но не взбивать). "))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/07.jpg","Форму с основой тщательно обернуть фольгой в два-три слоя. Влить начинку в форму. Поставить в форму, бóльшую по размеру (или в глубокий противень), влить горячую воду в большую форму, чтобы она доходила до середины формы. Поставить в разогретую до 160 градусов духовку (только нижний нагрев). Выпекать примерно 1 час 20 минут или ориентируйтесь по своей духовке. Готовый чизкейк оставить остывать в духовке (дверцу слегка приоткрыть)."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/08.jpg","Вот так выглядит верх идеального чизкейка — абсолютно гладкая, ровная и слегка кремовая поверхность, именно для этого мы и выпекаем его на водяной бане и при невысокой температуре."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/09.jpg","Готовим мусс.Всю смородину — 750 г (500 г для мусса и 250 г для желе) выложить в емкость, и измельчить блендером."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/10.jpg","Тщательно протереть через дуршлаг, чтобы избавится от косточек и шкурки. Из жмыха можно сварить компот. Протертую массу разделить обратно на 2/3 для мусса и 1/3 часть для желе."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/11.jpg","Сливки взбить до значительной густоты, подсыпая сахар."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/12.jpg","Добавить протертую смородину, взбить. Желатин предварительно замочить в 80 мл воды, оставить на 10 минут или на время, указанное на упаковке. Затем нагреть до горячего состояния, чтобы желатин растворился. Остудить. Влить желатин (лить на венчики, продолжая взбивать), взбить еще немного."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/13.jpg","Перелить полученную массу в форму, поставить в холодильник до полного застывания."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/14.jpg","Готовим желе. Желатин предварительно замочить в 50 мл воды, оставить на 10 минут или на время, указанное на упаковке. Затем нагреть до горячего состояния, чтобы желатин растворился. Остудить. К оставшейся части смородины добавить сахар, хорошо перемешать, пока сахар не растворится, затем влить желатин, перемешать. Вылить полученную массу поверх мусса, поставить в холодильник до полного застывания."))
                        .addStep(new Step("http://kamelena.ru/recipes/image/500/507/15.jpg","Когда полностью застынет, прогреть бортик формы феном или провести внутри вдоль бортика тонким ножом, расстегнуть форму, и переложить чизкейк на блюдо.Вот такой красавец получается, с красивой глянцевой поверхностью!"))
                        .commitStep()));



        this.recipes = recipes;
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("куриные грудки",1));
        ingredients.add(new Ingredient("сливочное масло",2));
        ingredients.add(new Ingredient("яйца",3));
        ingredients.add(new Ingredient("панировочные сухари",4));
        ingredients.add(new Ingredient("мука",5));
        ingredients.add(new Ingredient("соль",6));
        ingredients.add(new Ingredient("черный перец",7));
        ingredients.add(new Ingredient("растительное масло",8));
        ingredients.add(new Ingredient("батон",9));
        ingredients.add(new Ingredient("твердый сыр",10));
        ingredients.add(new Ingredient("помидоры",12));
        ingredients.add(new Ingredient("свежие огурци",13));
        ingredients.add(new Ingredient("репчатый лук",14));
        ingredients.add(new Ingredient("болгарский перец",15));
        ingredients.add(new Ingredient("фета",16));
        ingredients.add(new Ingredient("греческие оливки",17));
        ingredients.add(new Ingredient("оливковое масло",18));
        ingredients.add(new Ingredient("сухое орегано",19));
        ingredients.add(new Ingredient("свинина",20));
        ingredients.add(new Ingredient("уксус",21));
        ingredients.add(new Ingredient("вода",22));
        ingredients.add(new Ingredient("сухофрукты",23));
        ingredients.add(new Ingredient("сахар",24));
        ingredients.add(new Ingredient("шоколад",25));
        ingredients.add(new Ingredient("сливки",26));
        ingredients.add(new Ingredient("молоко",27));
        ingredients.add(new Ingredient("крахмал",28));
        ingredients.add(new Ingredient("крабовые палочки",29));
        ingredients.add(new Ingredient("вареное яйцо",30));
        ingredients.add(new Ingredient("чеснок",31));
        ingredients.add(new Ingredient("майонез",32));
        ingredients.add(new Ingredient("клубника",33));
        ingredients.add(new Ingredient("творог",34));
        ingredients.add(new Ingredient("песочное печенье",35));
        ingredients.add(new Ingredient("сахарная пудра",36));
        ingredients.add(new Ingredient("кокосовая стружка",37));
        ingredients.add(new Ingredient("какао",38));
        ingredients.add(new Ingredient("черная смородина",39));
        ingredients.add(new Ingredient("желатин",40));

    }

    private RecipeBuilder startIngredients(){
        targetIngredients = new ArrayList<>();
        return this;
    }
    private RecipeBuilder addIngredient(Ingredient ingredient){
        targetIngredients.add(ingredient);
        return this;
    }
    private List<Ingredient> commitIngredient(){
        return targetIngredients;
    }
    private RecipeBuilder startSteps(){
        targetSteps = new ArrayList<>();
        return this;
    }
    private RecipeBuilder addStep(Step step){
        targetSteps.add(step);
        return this;
    }
    private List<Step> commitStep(){
        return targetSteps;
    }
    public List<Recipe> getRecipes(){
        return recipes;
    }
    public List<Ingredient> getIngredients(){
        return ingredients;
    }
}

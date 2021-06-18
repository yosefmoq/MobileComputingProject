package com.app.mobilecomputingproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;

import com.app.mobilecomputingproject.R;
import com.app.mobilecomputingproject.databinding.ActivitySplashBinding;
import com.app.mobilecomputingproject.helper.MyDatabase;
import com.app.mobilecomputingproject.helper.sharedPre.LocalSave;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding activitySplashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashBinding = ActivitySplashBinding.inflate(LayoutInflater.from(this), null, false);
        setContentView(activitySplashBinding.getRoot());
        getSupportActionBar().hide();
        activitySplashBinding.setAppName(getString(R.string.app_name));

        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(!LocalSave.getInstance(SplashActivity.this).isFirst()){
                    LocalSave.getInstance(SplashActivity.this).setFirstTime(true);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","باب الساهرة: يقع إلى الجانب الشمالي من سور القدس، على بعد نصف كيلو متر شرقي باب العمود. وهو بسيط البناء، ضمن برج مربع، ويرجع إلى عهد السلطان سليمان القانوني، ويعرف أيضا باسم باب هيرودوتس.",1);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq"," باب الأسباط: يقع في الحائط الشرقي، ويشبه في الشكل باب الساهرة، يعود تاريخه إلى عهد السلطان سليمان القانوني. وسمي أيضا باب القديس، إسطيفان، وباب الأسود؛ لوجود تمثالين لأسدين على جانبي مدخله.",1);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq"," باب المغاربة: يقع في الحائط الجنوبي لسور القدس، وهو عبارة عن قوس قائم ضمن برج مربع، ويعتبر أصغر أبواب القدس.",1);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","اب النبي داود: وهو باب كبير منفرج، أنشئ في عهد السلطان سليمان القانوني، ويسمى أيضا باب صهيون.",1);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","اب الخليل: يقع في الحائط الغربي، ويسمى أيضا باب يافا",1);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","باب الحديد: يقع في الجانب الشمالي للسور، على مسافة كيلو متر غربي باب العمود. فتح عام 1898م إبان زيارة الإمبراطور الألماني (غليوم الثاني) للقدس.",1);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","باب العمود: وهو من أهمّ وأقدم بوابات القدس وأجملها، فقد تمّ بناؤه في العام 1542م في عهد السلطان سليمان القانوني، وسُميت بهذا الاسم نسبةً إلى العمود الذي كان ينتصب أمام الباب تخليداً لانتصارات الجيش الروماني",1);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","معركة اليرموك سميت هذه المعركة بمعركة اليرموك بسبب وقوعها على أرض اليرموك وقعت هذه المعركة بين المسلمين و البيزنطيين على أحد ضفاف نهر اليرموك، حيث قام هرقل ملك الروم بتجميع أعداد كبيرة من الجيوش بقيادة ماهان، لإخراج المسلمين من بلاد الشام تراوح عدد البيزنطيين في هذه المعركة ما بين 100 ألف إلى 400 ألف، أما المسلمين كان عددهم ما بين 24 ألف إلى 46 ألف تمكن البيزنطيون من تحقيق النصر في البداية بسبب كثرة عددهم، لكن بعد ذلك قاد خالد بن الوليد جيش المسلمين، وقام بالهجوم على جيش الروم، و حول الهزيمة إلى نصر عظيم",2);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","معركة حطين من معارك فلسطين التي حدثت هي حطين و سميت بهذا الإسم بسبب وقوعها قرب منطقة حطين بين الناصرة و طبريا وقعت هذه المعركة بين المسلمين بقيادة صلاح الدين الأيوبي و الصليبيين عام 1187م، حيث كان صلاح الدين يريد تحرير أراضي القدس من أيدي الصليبيين، و تمكن من إحراز النصر و تحرير بعض الأراضي من أيدي الصليبيين",2);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","معركة شعفاط في عام 1948 شن الجيش المناضل هجوماً على قافلة صهيونية قرب منطقة شعفاط، كانت تقل الإمدادات من مدينة القدس إلى مستوطنة النبي يعقوب عندما وقفت القافلة هجم عليها المناضلون، وقاموا بقتل 14 صهيوني، وأحرقوا المصفحتين اللتين كانتا تحميان القافلة، وأخذوا ما بها من أسلحة",2);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","معركة باب الواد يعتبر باب الواد ممر يقوم بربط السهل الساحلي بجبال القدس، و تتفرع منه العديد من الطرق : القدس، و الرملة، و بيت جبرين، و عرطوف، و غزة و رام الله. وقعت هذه المعركة بسبب نيّة الصهاينة باحتلال باب الواد، فتجمع الجيش الأردني وكان عددهم 300 مقاتل بقيادة شيخ من قبيلة الحويطات يدعى هارون بن جازي، وانضموا إلى الجيش الفلسطيني بقيادة عبد القادر الحسيني شن العرب الهجوم على الصهاينة، و قامو بإتلاف الطرق التي توصل إلى المدن اليهودية، و عملوا على تفجير السيارات بالألغام انتهت هذه المعركة بانتصار العرب، و أدت إلى خسائر فادحة في الجيش الصهيوني",2);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","معركة بيسان وقعت هذه المعركة عندما قام 300 مقاتل بريطاني بالتحرك إلى تل الحصن و احتلاله، و بدأوا بقصف منطقة بيسان، وذلك لشن الهجوم عليها كان في بيسان آنذاك 175 رجل من الأردنيين والفلسطينيين، قاموا برد الهجوم عن بيسان للمرة الأولى، لكن المرة الثانية كان هجوم الصهاينة أكثف و أقوى، و لم يتمكن العرب من صده، حيث دخل الصهاينة على بيسان و جمعوا أسلحة المجاهدين",2);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","هي عاصمة الدولة الفلسطينية وأكبر مدنها التاريخية، وتحتلّ المكان الأكبر بين مدنها، حيث تبلغ المساحة الكلية لها حوالي 19.331كم²، وهي مهد الديانات السماوية الثلاثة الإسلامية والمسيحية واليهودية، وقد تعرّضت المدينة عبر الأزمان للعديد من الهجمات،فقد تمّ تدميرها مرتين وحوصرت ما يقارب 23 مرةً، وتم مهاجمتها 52 مرةً وتعرّضت للغزو ما يقارب 44 مرةً، أسسها الكنعانيون قبل ما يقارب 3000 عام قبل الميلاد، وسكنها اليبوسيون فسُميت نسبة إليهم باسم (يبوس)، وكانت القدس منذ القدم محط أطماع الكثيرين كالصليبيين، والفرس والرومان، وللقدس أسماء عديدة منها: بيت المقدس، وأولى القبلتين، والقدس الشريف",3);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","مسجد قبة الصخرة: وهو جزءٌ من المسجد الأقصى، وتعتبر القبة من أهمّ معالم القدس، حيث تقع القبة داخل الحرم القدسي الشريف، وتتميز بوجود الصخرة المشرفة داخلها والتي عرج منها الرسول الكريم محمد صلى الله عليه وسلم إلى السماء في ليلة الإسراء والمعراج، وقد تمّ بناء هذه القبة في عهد الخليفة عبد الملك بن مروان، وتمتاز القبة بطلائها بالذهب اللامع، والفن المعماري والزخرفة الإسلامية التي تزينها والآيات القرآنية الكريمة.",4);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","المسجد القبلي: وهو جزءٌ من المسجد الأقصى والذي يعتبر أولى القبلتين وثالث أقدس المساجد على الأرض، وقد تمّ بناؤه في عهد عبد الملك بن مروان",4);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","باب العمود: وهو من أهمّ وأقدم بوابات القدس وأجملها، فقد تمّ بناؤه في العام 1542م في عهد السلطان سليمان القانوني، وسُميت بهذا الاسم نسبةً إلى العمود الذي كان ينتصب أمام الباب تخليداً لانتصارات الجيش الروماني",4);
                    new MyDatabase(SplashActivity.this).addRow("yosefMoq","كنيسة القيامة: وهي من الأماكن المقدسة بالنسبة للمسيحيين، حيث تقع على تلة الجلجة، ويحجّ إليها المسيحيون من جميع أنحاء العالم منذ القرن الرابع، وتحتوي الكنيسة على المكان الذي دفن فيه يسوع.",4);
                }
                startActivity(MainActivity.getMainIntent(SplashActivity.this));
                finish();
            }
        }.start();

    }
}
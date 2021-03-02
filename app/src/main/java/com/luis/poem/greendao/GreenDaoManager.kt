
import android.content.Context
import com.luis.poem.bean.PoemBean
import com.luis.poem.greendao.DaoMaster
import com.luis.poem.greendao.PoemBeanDao


class GreenDaoManager {
    private lateinit var poemBeanDao: PoemBeanDao


    constructor(ctx: Context) {
        //创建一个db 数据库
        //通过DaoMaster内部类DaoMaster.DevOpenHelper创建一个SqliteOpenHelper类实例， 通过openhelper获取数据db
        val database = DaoMaster.DevOpenHelper(ctx, "poem.db", null).writableDatabase
        val daoMaster = DaoMaster(database)
        val daoSession = daoMaster.newSession()
        poemBeanDao = daoSession.poemBeanDao
    }

    //通过伴生对象，实现单例
    companion object {
        private var instance: GreenDaoManager? = null
        public fun getInstance(ctx: Context): GreenDaoManager {
            if (instance == null) {
                instance = GreenDaoManager(ctx)
            }
            return instance!!
        }
    }

    //添加方法
    fun addPoem(poemBean: PoemBean) {
        poemBeanDao.insert(poemBean)
    }
    fun queryAllDatas(): List<PoemBean> {
        return poemBeanDao.queryBuilder() // 查询 User
            .orderAsc(PoemBeanDao.Properties.Id) // 末名升序排列
            .list() // 返回集合
    }

    fun query20Datas(pageIndex:Int,count:Int): List<PoemBean> {
        return poemBeanDao.queryBuilder() // 查询 User
            .offset((pageIndex-1)*count)
            .orderAsc(PoemBeanDao.Properties.Id) // 末名升序排列
            .limit(20)
            .list() // 返回集合
    }

    fun findPoemByOid(oid: String): List<PoemBean> {
        return poemBeanDao.queryBuilder() // 查询 User
            .where(PoemBeanDao.Properties._id.eq(oid)).list()
    }

    fun findPoem(writer: String): MutableList<PoemBean> {
        val Userlist = poemBeanDao.queryRaw("where writer=?", writer)
        return Userlist
    }
}

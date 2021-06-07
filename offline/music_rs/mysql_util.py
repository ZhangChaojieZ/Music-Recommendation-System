# python使用pymysql操作数据库MySQL
import random
import time

import pymysql
import util


def test_connect():
    """
    测试数据库连接
    :return:
    """
    # 打开数据库连接
    conn = pymysql.connect(host='localhost', port=3306, database='music_rs', user='root', password='root',
                           charset='utf8')
    # 获得Cursor对象
    cs = conn.cursor()
    a = '123'
    b = '123'
    c = '123'
    sql = 'insert into user (uid,name,password) values (%s,%s,%s)'
    param = (a, b, c)
    cs.execute(sql, param)
    conn.commit()
    cs.close()
    conn.close()


def read_user_info2mysql():
    """
        读取用户信息并写入到数据库的user表中
        :return:
        """
    # 打开数据库连接
    conn = pymysql.connect(host='localhost', port=3306, database='music_rs', user='root', password='root',
                           charset='utf8')
    # 获得Cursor对象
    cs = conn.cursor()

    # 用户文件路径名
    user_file_name = './dataset/user_info.txt'
    # sql语句
    sql = 'insert into user (uid,name,password) values (%s,%s,%s)'

    # 读取文件存入数据库
    with open(user_file_name, 'r', encoding='utf-8') as f:
        for line in f:
            user = line.strip().split('\t')
            uid = user[0]
            name = user[1]
            password = '123'
            # 定义一个参数元组
            param = (uid, name, password)
            print(uid + '写入数据库···')
            cs.execute(sql, param)
    # 增、删、改类操作需要提交事务
    conn.commit()
    # 关闭文件
    f.close()
    cs.close()
    # 关闭数据库
    conn.close()


def read_singer_info2mysql():
    """
        读取歌手信息并写入到数据库的singer表中
        :return:
        """
    # 打开数据库连接
    conn = pymysql.connect(host='localhost', port=3306, database='music_rs', user='root', password='root',
                           charset='utf8')
    # 获得Cursor对象
    cs = conn.cursor()

    # 用户文件路径名
    sing_file_name = './dataset/singer_info.txt'
    # sql语句
    sql = 'insert into singer (suid,sname,surl) values (%s,%s,%s)'

    # 读取文件存入数据库
    with open(sing_file_name, 'r', encoding='utf-8') as f:
        for line in f:
            singer = line.strip().split('\t')
            suid = singer[0]
            sname = singer[1]
            surl = singer[2]
            # 定义一个参数元组
            param = (suid, sname, surl)
            print(suid + '写入数据库···')
            cs.execute(sql, param)
    # 增、删、改类操作需要提交事务
    conn.commit()
    # 关闭文件
    f.close()
    cs.close()
    # 关闭数据库
    conn.close()


def read_song_info2mysql():
    """
        读取歌曲信息并写入到数据库的song表中
        :return:
        """
    # 打开数据库连接
    conn = pymysql.connect(host='localhost', port=3306, database='music_rs', user='root', password='root',
                           charset='utf8')
    # 获得Cursor对象
    cs = conn.cursor()

    # 用户文件路径名
    song_file_name = 'dataset/new_min_song_info.txt'
    # sql语句
    sql = 'insert into song (iid,sname,surl,suid,playcnt,album) values (%s,%s,%s,%s,%s,%s)'

    # 读取文件存入数据库
    with open(song_file_name, 'r', encoding='utf-8') as f:
        for line in f:
            song = line.strip().split('\t')
            iid = song[0]
            sname = song[1]
            surl = song[2]
            splaycnt = song[4]
            salbum = song[3]
            suid = song[5]

            # 定义一个参数元组
            param = (iid, sname, surl, suid, splaycnt, salbum)
            print(iid + '写入数据库···')
            cs.execute(sql, param)
    # 增、删、改类操作需要提交事务
    conn.commit()
    # 关闭文件
    f.close()
    cs.close()
    # 关闭数据库
    conn.close()


def read_record2mysql():
    """
        读取播放记录并写入到数据库的record表中
        :return:
        """
    # 打开数据库连接
    conn = pymysql.connect(host='localhost', port=3306, database='music_rs', user='root', password='root',
                           charset='utf8')
    # 获得Cursor对象
    cs = conn.cursor()

    # 用户文件路径名
    record_file_name = './dataset/min_user_record.txt'
    # sql语句
    sql = 'insert into record (uid,iid,weight,timestamp) values (%s,%s,%s,%s)'

    # 读取文件存入数据库
    with open(record_file_name, 'r', encoding='utf-8') as f:
        for line in f:
            record = line.strip().split('\t')
            uid = record[0]
            iid = record[1]
            weight = record[2]
            print(weight)
            timestamp = record[3]
            # 定义一个参数元组
            param = (uid, iid, weight, timestamp)
            print(uid + '写入数据库···')
            cs.execute(sql, param)
    # 增、删、改类操作需要提交事务
    conn.commit()
    # 关闭文件
    f.close()
    cs.close()
    # 关闭数据库
    conn.close()


def read_top_users2mysql():
    """
        读取好友推荐结果并写入到数据库的topusers表中
        :return:
        """
    # 打开数据库连接
    conn = pymysql.connect(host='localhost', port=3306, database='music_rs', user='root', password='root',
                           charset='utf8')
    # 获得Cursor对象
    cs = conn.cursor()

    # 用户文件路径名
    top_users_file_name = './resultset/topN_users_baseline.txt'
    # sql语句
    sql = 'insert into topusers (uid,topusers) values (%s,%s)'

    # 读取文件存入数据库
    with open(top_users_file_name, 'r', encoding='utf-8') as f:
        for line in f:
            top_user = line.strip().split('\t')
            uid = top_user[0]
            topuers = top_user[1]
            # 定义一个参数元组
            param = (uid, topuers)
            print(uid + '写入数据库···')
            cs.execute(sql, param)
    # 增、删、改类操作需要提交事务
    conn.commit()
    # 关闭文件
    f.close()
    cs.close()
    # 关闭数据库
    conn.close()


def read_top_songs2mysql():
    """
        读取歌曲推荐结果并写入到数据库的topsongs表中
        :return:
        """
    # 打开数据库连接
    conn = pymysql.connect(host='localhost', port=3306, database='music_rs', user='root', password='root',
                           charset='utf8')
    # 获得Cursor对象
    cs = conn.cursor()

    # 用户文件路径名
    top_songs_file_name = './resultset/topN_songs_baseline.txt'
    # sql语句
    sql = 'insert into topsongs (iid,topsongs) values (%s,%s)'

    # 读取文件存入数据库
    with open(top_songs_file_name, 'r', encoding='utf-8') as f:
        for line in f:
            top_song = line.strip().split('\t')
            iid = top_song[0]
            topsongs = top_song[1]
            # 定义一个参数元组
            param = (iid, topsongs)
            print(iid + '写入数据库···')
            cs.execute(sql, param)
    # 增、删、改类操作需要提交事务
    conn.commit()
    # 关闭文件
    f.close()
    cs.close()
    # 关闭数据库
    conn.close()


def set_songs_down_url():
    """
    设置歌曲播放链接
    :return:
    """
    # 打开数据库连接
    conn = pymysql.connect(host='localhost', port=3306, database='music_rs', user='root', password='root',
                           charset='utf8')
    # 获得Cursor对象
    cs = conn.cursor()

    with open('./dataset/songs_download_url.txt', 'r', encoding='utf-8') as f:
        for line in f:
            song_id = line.split('\t')[0]
            down_url = line.split('\t')[1].strip()
            print("更新：" + song_id)
            # sql语句
            sql = "update song set down_url = '{}' where iid = '{}'".format(down_url, song_id)
            cs.execute(sql)
    conn.commit()
    conn.close()
    f.close()


def set_songs_time_picRlr():
    """
    设置歌曲播放链接
    :return:
    """
    # 打开数据库连接
    conn = pymysql.connect(host='localhost', port=3306, database='music_rs', user='root', password='root',
                           charset='utf8')
    # 获得Cursor对象
    cs = conn.cursor()

    with open('./dataset/songs_time_picurl.txt', 'r', encoding='utf-8') as f:
        for line in f:
            song_id = line.split('\t')[0].strip()
            song_time = line.split('\t')[1].strip()
            song_picurl = line.split('\t')[2].strip()
            print("更新：" + song_id)
            # sql语句
            sql = "update song set song_time = '{}',picUrl = '{}' where iid = '{}'".format(song_time, song_picurl,
                                                                                           song_id)
            cs.execute(sql)
    conn.commit()
    conn.close()
    f.close()


def set_songs_publish_time():
    """
    添加歌曲的发行时间
    :return:
    """
    # 打开数据库连接
    conn = pymysql.connect(host='localhost', port=3306, database='music_rs', user='root', password='root',
                           charset='utf8')
    # 获得Cursor对象
    cs = conn.cursor()
    with open("./dataset/songs_publish_time.txt", 'r', encoding='utf-8') as f:
        for line in f:
            song_id = line.split('\t')[0]
            song_publish_time = line.split('\t')[1].strip()
            print("更新：" + song_id)
            # sql语句
            sql = "update song set publishTime = '{}' where iid = '{}'".format(song_publish_time, song_id)
            cs.execute(sql)
    conn.commit()
    conn.close()
    f.close()


def update_user_info():
    """
    更新用户信息，添加性别、年龄等信息
    :return:
    """
    genders = ['男', '女']

    # 打开数据库连接
    conn = pymysql.connect(host='localhost', port=3306, database='music_rs', user='root', password='root',
                           charset='utf8')
    # 获得Cursor对象
    cs = conn.cursor()
    with open("./dataset/user_info.txt", 'r', encoding='utf-8') as f:
        for line in f:
            user_id = line.split('\t')[0]
            gender = random.choice(genders)
            age = random.randint(16, 30)
            province, city = util.get_random_city()
            des = '这个人很懒，什么也没有写'
            registerTime = int(round(time.time() * 1000))
            print("更新：" + user_id)
            # sql语句
            sql = "update user set gender = '{}',age = '{}',area = '{}',des = '{}',registerTime = '{}' where uid = '{}'".format(
                gender, age, province + '-' + city, des, registerTime, user_id)
            cs.execute(sql)
    conn.commit()
    conn.close()
    f.close()

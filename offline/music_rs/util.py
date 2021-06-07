# 工具类
import random
import time


def add_timestamp():
    """
    给用户播放记录添加时间戳
    :return:
    """
    # 文件路径名
    file_name = './dataset/user_record_init.txt'
    # 打开文件
    with open(file_name, 'r', encoding='utf-8') as in_f, open('./dataset/user_record.txt', 'a',
                                                              encoding='utf-8') as out_f:
        for line in in_f:
            # 添加时间戳
            line = line.strip() + '\t' + str(int(time.time()))
            # 写入到结果文件中
            out_f.write(line + '\n')
            out_f.flush()
        # 关闭文件
        in_f.close()
        out_f.close()


# add_timestamp()

def remove_user_not_in_record():
    """
    去除user_info.txt中不在user_record_init.txt中的用户
    :return:
    """
    # 文件路径名
    user_info_file_name = './dataset/user_info.txt'
    user_record_file_name = './dataset/user_record.txt'

    # 播放记录中的用户id列表
    user_id_list = []
    with open(user_record_file_name, 'r', encoding='utf-8') as in_f:
        for line in in_f:
            if line.split('\t')[0] not in user_id_list:
                user_id_list.append(line.split('\t')[0])
    in_f.close()

    # 在播放记录中的用户信息
    user_info_list = []
    with open(user_info_file_name, 'r', encoding='utf-8') as out_f:
        for line in out_f:
            if line.split('\t')[0] in user_id_list:
                user_info_list.append(line)
    out_f.close()

    # 将筛选之后的用户信息添加到用户信息文件中，覆盖原内容
    with open(user_info_file_name, 'w', encoding='utf-8') as out_f:
        for line in user_info_list:
            out_f.write(line.strip() + '\n')
        out_f.flush()
    out_f.close()


# remove_user_not_in_record()

def remove_song_same():
    """
    去除song_info.txt中重复的歌曲
    :return:
    """
    # 文件路径名
    song_info_file_name = './dataset/song_info.txt'

    # 歌曲中id列表
    song_id_list = []
    # 歌曲音乐信息，内容唯一
    song_lines = []
    with open(song_info_file_name, 'r', encoding='utf-8') as f:
        for line in f:
            if line.split('\t')[0] not in song_id_list:
                song_id_list.append(line.split('\t')[0])
                song_lines.append(line)
    f.close()

    # 将筛选之后的音乐数据覆盖掉原来的音乐数据
    with open(song_info_file_name, 'w', encoding='utf-8') as f:
        for line in song_lines:
            f.write(line)
            f.flush()
    f.close()


# remove_song_same()

def get_min_song_info():
    """
    得到音乐信息的子集，将每个用户的听歌记录缩减至30首
    :return:
    """
    # 文件路径名
    song_info_file_name = './dataset/song_info.txt'
    record_file_name = './dataset/user_record.txt'

    # 歌曲中id列表
    song_id_list = []
    # 歌曲音乐信息，内容唯一
    song_lines = []
    user_id = []
    i = 0
    print('读播放记录文件')
    with open(record_file_name, 'r', encoding='utf-8') as f:
        for line in f:
            if line.split('\t')[0] not in user_id:
                user_id.append(line.split('\t')[0])
                i = 0
            # 获取播放次数最多的前30首
            print('前30首')
            if i < 30 and line.split('\t')[0] in user_id:
                song_id_list.append(line.split('\t')[1])
                i = i + 1
    f.close()

    # 筛选数据
    print('读音乐信息文件')
    with open(song_info_file_name, 'r', encoding='utf-8') as f:
        for line in f:
            if line.split('\t')[0] in song_id_list:
                song_lines.append(line)
    f.close()

    # 将筛选之后的音乐数据覆盖掉原来的音乐数据
    print('生成新的音乐信息文件')
    with open('dataset/min_song_info.txt', 'a', encoding='utf-8') as f:
        for line in song_lines:
            f.write(line)
            f.flush()
    f.close()


# get_min_song_info()


def get_min_user_record():
    """
    减少用户播放记录，每个用户30首
    :return:
    """
    record_file_name = './dataset/user_record.txt'
    min_record_file_name = './dataset/min_user_record.txt'
    user_id = []
    user_record_line = []
    print('读播放记录文件')
    with open(record_file_name, 'r', encoding='utf-8') as f, open(min_record_file_name, 'a', encoding='utf-8') as o_f:
        for line in f:
            if line.split('\t')[0] not in user_id:
                user_id.append(line.split('\t')[0])
                i = 0
            # 获取播放次数最多的前30首
            print(line.split('\t')[0] + '的前30首')
            if i < 30 and line.split('\t')[0] in user_id:
                o_f.write(line)
                o_f.flush()
                i = i + 1
    f.close()
    o_f.close()


# get_min_user_record()

def get_singer_info():
    """
    得到歌手信息的文件
    :return:
    """
    song_info_file_name = 'dataset/min_song_info.txt'
    singer_info_file_name = './dataset/singer_info.txt'
    singers_id = []
    with open(song_info_file_name, 'r', encoding='utf-8') as in_f, open(singer_info_file_name, 'a',
                                                                        encoding='utf-8') as out_f:
        for line in in_f:
            if line.split('\t')[3] not in singers_id:
                singer_id = line.split('\t')[3]
                singer_name = line.split('\t')[4]
                singer_url = line.split('\t')[5].strip()
                singers_id.append(singer_id)
                singer = singer_id + '\t' + singer_name + '\t' + singer_url
                out_f.write(singer + '\n')
                out_f.flush()
    in_f.close()
    out_f.close()


# 随机获得一个省市地区
def get_random_city():
    """
    随机获得一个省市地区
    :return:
    """
    area = {
        '河南省': ['周口', '郑州', '洛阳', '开封', '新乡', '信阳'],
        '湖北省': ['武汉', '孝感', '黄石', '仙桃', '襄阳'],
        '浙江省': ['杭州', '绍兴', '义乌', '宁波', '嘉兴'],
        '江苏省': ['南京', '无锡', '芜湖', '徐州', '盐城'],
        '四川省': ['成都', '绵阳', '乐山'],
        '安徽省': ['合肥', '阜阳', '滁州', '亳州'],
        '山西省': ['太原', '大同', '长治', '晋城'],
        '陕西省': ['西安', '宝鸡', '榆林', '吕梁'],
        '河北省': ['石家庄', '唐山', '秦皇岛', '承德'],
        '山东省': ['济南', '青岛', '临沂', '淄博'],
        '湖南省': ['长沙', '衡阳', '湘潭', '邵阳', '岳阳', '株洲'],
        '江西省': ['南昌', '九江', '上饶', '景德镇']}
    area_keys = list(area.keys())
    # 省份
    province = random.choice(area_keys)
    # 城市
    city = random.choice(area.get(province))
    return province, city

# print(get_random_city())
from database import db

def generate_next_id(prefix: str, collection_name: str, field: str = "user_id"):
    collection = db[collection_name]
    last = collection.find({field: {"$regex": f"^{prefix}_"}}).sort(field, -1).limit(1)
    last_id = None
    for doc in last:
        last_id = doc.get(field)

    if last_id:
        last_num = int(last_id.split("_")[1])
        new_num = f"{last_num + 1:02d}"
    else:
        new_num = "01"

    return f"{prefix}_{new_num}"

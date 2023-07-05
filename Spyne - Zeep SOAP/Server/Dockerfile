FROM python:3
ADD server.py
RUN pip install spyne
RUN pip install mysqlclient
RUN pip install logging
RUN pip install wsgiref
CMD ["python", "./server.py"]